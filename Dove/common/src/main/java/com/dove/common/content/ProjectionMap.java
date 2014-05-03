package com.dove.common.content;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A convenience wrapper for a projection map. Makes it easier to create and use projection maps.
 */
public class ProjectionMap extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;
    /**
     * The projection contains columns aliase.
     */
    private String[] mColumns;

    /**
     * @return A new {@link com.dove.common.content.ProjectionMap.Builder} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return THe projection columns aliase.
     */
    public String[] getColumnNames() {
        return mColumns;
    }

    private void putColumn(String alias, String column) {
        super.put(alias, column);
    }

    /**
     * Not supported, call this method will throw
     * {@link UnsupportedOperationException}. Use the
     * {@link com.dove.common.content.ProjectionMap.Builder#add(String, String)}
     * to build a projection map.
     */
    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported, call this method will throw
     * {@link UnsupportedOperationException}. Use the
     * {@link com.dove.common.content.ProjectionMap.Builder#addAll(com.dove.common.content.ProjectionMap)}
     * to build a projection map.
     */
    @Override
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException();
    }

    public static class Builder {
        private final ProjectionMap mMap = new ProjectionMap();

        /**
         * Add a column projection with the format "column As column".
         *
         * @param column The column to be projected.
         * @return This Builder to build a {@link com.dove.common.content.ProjectionMap}.
         */
        public Builder add(String column) {
            mMap.put(column, column);
            return this;
        }

        /**
         * Add a column projection with the format "alias As expression".
         *
         * @param alias The column's alias.
         * @return This Builder to build a {@link com.dove.common.content.ProjectionMap}.
         */
        public Builder add(String alias, String expression) {
            mMap.put(alias, expression + " AS " + alias);
            return this;
        }

        /**
         * Append all the columns to this Builder.
         *
         * @param columns Columns to be appended.
         * @return This Builder to build a {@link com.dove.common.content.ProjectionMap}.
         */
        public Builder addAll(String[] columns) {
            for (String column : columns) {
                add(column);
            }
            return this;
        }

        /**
         * Append all the pre-projected columns into this builder.
         *
         * @param map Previous projection map.
         * @return This Builder to build a {@link com.dove.common.content.ProjectionMap}.
         */
        public Builder addAll(ProjectionMap map) {
            for (Entry<String, String> entry : map.entrySet()) {
                mMap.putColumn(entry.getKey(), entry.getValue());
            }
            return this;
        }

        /**
         * Construct the {@link com.dove.common.content.ProjectionMap} instance.
         */
        public ProjectionMap build() {
            final String[] columns = new String[mMap.size()];
            mMap.keySet().toArray(columns);
            Arrays.sort(columns);
            mMap.mColumns = columns;
            return mMap;
        }
    }
}
