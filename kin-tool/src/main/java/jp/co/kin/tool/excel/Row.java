package jp.co.kin.tool.excel;

import java.util.ArrayList;
import java.util.List;

import jp.co.kin.tool.type.CellPositionType;

/**
 * Row
 *
 * @since 1.0.0
 */
public class Row {

    /** セルリスト */
    private List<Cell> cellList = new ArrayList<>();

    /**
     * セルを追加する
     *
     * @param cell
     *     セル
     */
    public void addCell(Cell cell) {
        this.cellList.add(cell);
    }

    /**
     * セルリストを返す
     *
     * @return cellList
     */
    public List<Cell> getCellList() {
        return cellList;
    }

    /**
     * セル位置に対応するセルを返す
     *
     * @param type
     *     セル位置
     * @return セル
     */
    public Cell getCell(CellPositionType type) {
        return this.cellList.get(type.getPosition());
    }
}
