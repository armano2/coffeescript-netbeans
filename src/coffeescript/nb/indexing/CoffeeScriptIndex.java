/*
 * Copyright(c) Zdenek Tronicek, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (CDDL). You may not use this file except in
 * compliance with the CDDL. You can obtain a copy of the CDDL at
 * http://www.netbeans.org/cddl.html.
 *
 */
package coffeescript.nb.indexing;

import coffeescript.nb.core.Constants;
import com.sun.source.tree.Tree.Kind;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.project.Project;
import org.netbeans.modules.parsing.spi.indexing.support.IndexResult;
import org.netbeans.modules.parsing.spi.indexing.support.QuerySupport;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Denis Stepanov
 */
public class CoffeeScriptIndex {

    public static final String CONTENT_KEY = "CONTENT_KEY"; //NOI18N
    public static final String HINT_KEY = "HINT_KEY"; //NOI18N
    public static final String KIND_KEY = "KIND"; //NOI18N

    public static CoffeeScriptIndex create(Project project) {
        try {
            return new CoffeeScriptIndex(project);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }
    private final QuerySupport querySupport;

    private CoffeeScriptIndex(Project project) throws IOException {
        Collection<FileObject> sourceRoots = QuerySupport.findRoots(project,
                null,
                Collections.<String>emptyList(),
                Collections.<String>emptyList());
        this.querySupport = QuerySupport.forRoots(CoffeeScriptIndexer.Factory.NAME, CoffeeScriptIndexer.Factory.VERSION, sourceRoots.toArray(new FileObject[]{}));
    }

    public Map<FileObject, Set<Kind>> getHintsWithKinds() {
        Map<FileObject, Set<Kind>> result = new HashMap<FileObject, Set<Kind>>();
        Collection<FileObject> fileObjects = new ArrayList<FileObject>();
        try {
            for (IndexResult indexResult : filterDeletedFiles(querySupport.query(CONTENT_KEY, "", QuerySupport.Kind.PREFIX))) {
                Set<Kind> kinds = new HashSet<Kind>();
                for (String value : indexResult.getValues(KIND_KEY)) {
                    kinds.add(Kind.valueOf(value));
                }
                result.put(indexResult.getFile(), kinds);
            }
            return result;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return Collections.emptyMap();
        }
    }

    public Collection<FileObject> getAll() {
        try {
            return returnAsFiles(querySupport.query(CONTENT_KEY, "", QuerySupport.Kind.PREFIX));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return Collections.emptyList();
        }
    }

    public Collection<FileObject> getAllByKind(String kind) {
        try {
            Collection<IndexResult> filtered = new LinkedList<IndexResult>();
            for (IndexResult indexResult : querySupport.query(HINT_KEY, Boolean.TRUE.toString(), QuerySupport.Kind.EXACT, KIND_KEY)) {
                for (String resultKind : indexResult.getValues(KIND_KEY)) {
                    if (kind.equals(resultKind)) {
                        filtered.add(indexResult);
                        continue;
                    }
                }
            }
            return returnAsFiles(filtered);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return Collections.emptyList();
        }
    }

    public Set<com.sun.source.tree.Tree.Kind> getAllKinds() {
        try {
            Set<com.sun.source.tree.Tree.Kind> kinds = new HashSet<com.sun.source.tree.Tree.Kind>();
            for (IndexResult indexResult : querySupport.query(HINT_KEY, Boolean.TRUE.toString(), QuerySupport.Kind.EXACT, KIND_KEY)) {
                String kind = indexResult.getValue(KIND_KEY);
                if (kind != null) {
                    kinds.add(com.sun.source.tree.Tree.Kind.valueOf(kind));
                }
            }
            return kinds;
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            return Collections.emptySet();
        }
    }

    protected Collection<FileObject> returnAsFiles(Collection<? extends IndexResult> results) {
        Collection<FileObject> rngFiles = new LinkedList<FileObject>();
        for (IndexResult result : filterDeletedFiles(results)) {
            if (result.getFile().getMIMEType().equals(Constants.MIME_TYPE)) {
                rngFiles.add(result.getFile());
            }
        }
        return rngFiles;
    }

    private Collection<? extends IndexResult> filterDeletedFiles(Collection<? extends IndexResult> queryResult) {
        Collection<IndexResult> filtered = new ArrayList<IndexResult>();
        for (IndexResult result : queryResult) {
            if (result.getFile() != null) {
                filtered.add(result);
            }
        }
        return filtered;
    }
}
