package br.com.eswiv.auxiliares;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.search.AbstractSearchable;
import org.jdesktop.swingx.search.TableSearchable;

/**
 * Custom searchable which supports per-column searching in a JXTable.
 */
public class SearchColumnTableSearchable extends TableSearchable {

    public final static int SEARCH_ALL_COLUMNS = -1;
    int searchColumn;

    /**
     * @param table
     */
    public SearchColumnTableSearchable(JXTable table) {
        this(table, SEARCH_ALL_COLUMNS);
    }

    /**
     * @param table
     * @param searchAllColumns
     */
    public SearchColumnTableSearchable(JXTable table, int searchColumn) {
        super(table);
        // TODO : add getters and setters for searchColumn
        this.searchColumn = table.convertColumnIndexToView(searchColumn);
    }

    /**
     * {@inheritDoc} <p>
     *
     * Overridden to support per-column searching.
     */
    @Override
    protected void findMatchAndUpdateState(Pattern pattern, int startRow,
            boolean backwards) {
        if (SEARCH_ALL_COLUMNS == searchColumn) {
            super.findMatchAndUpdateState(pattern, startRow, backwards);
        } else {
            AbstractSearchable.SearchResult result = findMatchInSearchColumn(pattern, startRow, backwards);
            updateState(result);
        }
    }

    /**
     * @param pattern
     * <code>Pattern</code> that we will try to locate
     * @param startRow position in the document in the appropriate
     * coordinates from which we will start search or -1 to start
     * from the beginning
     * @param backwards
     * <code>true</code> if we should perform search
     * towards the beginning
     */
    private AbstractSearchable.SearchResult findMatchInSearchColumn(Pattern pattern,
            int startRow, boolean backwards) {
        AbstractSearchable.SearchResult searchResult = null;
        if (backwards) {
            for (int index = startRow; index >= 0 && searchResult == null; index--) {
                searchResult = findMatchAt(pattern, index, searchColumn);
            }
        } else {
            for (int index = startRow; index < getSize() && searchResult == null; index++) {
                searchResult = findMatchAt(pattern, index, searchColumn);
            }
        }
        return searchResult;
    }

    @Override
    protected SearchResult findMatchAt(Pattern pattern, int row, int column) {
        String text = table.getStringAt(row, column);
        if ((text != null) && (text.length() > 0)) {
            pattern = Pattern.compile("^"+pattern.pattern()+"$");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return createSearchResult(matcher, row, column);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc} <p>
     */
    @Override
    protected int moveStartPosition(int startIndex, boolean backwards) {
        if (backwards) {
            return super.moveStartPosition(startIndex, backwards);
        } else {
            lastSearchResult.resetFoundColumn();
            if (backwards) {
                startIndex--;
            } else {
                startIndex++;
            }
            return startIndex;

        }
    }
}