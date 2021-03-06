package jp.co.kin.tool.build;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import jp.co.kin.common.type.LineFeedType;
import jp.co.kin.common.util.FileUtil.FileExtension;
import jp.co.kin.common.util.StringUtil;
import jp.co.kin.tool.build.annotation.Build;
import jp.co.kin.tool.config.FileConfig;
import jp.co.kin.tool.db.Column;
import jp.co.kin.tool.db.Table;
import jp.co.kin.tool.excel.Excel;
import jp.co.kin.tool.excel.Row;
import jp.co.kin.tool.factory.FileFactory;
import jp.co.kin.tool.type.CellPositionType;
import jp.co.kin.tool.type.ExecuteType;

/**
 * CreateTableBuilder
 *
 * @since 1.0.0
 *
 */
public class CreateTableBuilder extends SqlSourceBuilder {

    @Build
    public void execute() {

        Excel excel = super.reader.read();
        excel.activeSheet("TABLE_LIST");

        for (String tableName : this.targetTableList) {
            Table table = toTable(excel.getRowList(), tableName);
            StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
            body.add(getTableComment(table.getLogicalName()));
            body.add("CREATE TABLE " + tableName + " (");
            StringJoiner columnData = new StringJoiner(
                    StringUtil.COMMA + LineFeedType.CRLF.getValue());
            table.getColumnList().stream().forEach(e -> {

                String comment = e.getComment();
                String name = e.getName();
                String type = e.getType();

                StringBuilder sb = new StringBuilder();
                sb.append("-- ");
                sb.append(comment);
                sb.append(StringUtil.NEW_LINE);
                sb.append(name);
                sb.append(StringUtil.SPACE);
                sb.append(type);
                sb.append(" comment '");
                sb.append(comment);
                sb.append("'");

                columnData.add(sb.toString());
            });
            body.add(columnData.toString());
            body.add(");");

            FileConfig conf = getFileConfig(ExecuteType.DDL);
            conf.setFileName(tableName.toUpperCase() + FileExtension.SQL.getValue());
            conf.setData(body.toString());
            FileFactory.create(conf);
        }
    }

    private Table toTable(List<Row> rowList, String tableName) {
        Table table = new Table();
        table.setPhysicalName(tableName);
        String logicalName = rowList.stream().filter(e -> isTargetTable(e, tableName))
                .map(e -> e.getCell(CellPositionType.LOGICAL_NAME))
                .collect(Collectors.toList()).get(0)
                .getValue();
        table.setLogicalName(logicalName);
        table.setColumnList(
                rowList.stream().filter(e -> isTargetTable(e, tableName)).map(e -> {
                    Column column = new Column();
                    column.setName(getColumnName(e));
                    column.setComment(getColumnComment(e));
                    column.setType(getColumnType(e));
                    return column;
                }).collect(Collectors.toList()));
        return table;
    }

}
