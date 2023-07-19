/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nekena
 */
public class EntityService {
    public static String search(Map<String, String> formData) {
    ArrayList<String> conditions = new ArrayList<>();
    for (Map.Entry<String, String> entry : formData.entrySet()) {
        String columnName = entry.getKey();
        String columnValue = entry.getValue().trim();
        if (!columnValue.isEmpty()) {
            String comparator ="";
            String columnnamefinal="";
            if (isDebut(columnName)) {
                columnnamefinal=columnName.substring(0, (columnName.length()-5));
                comparator=">=";
            }else if (isFin(columnName)) {
                columnnamefinal=columnName.substring(0, (columnName.length()-3));
                comparator="<=";
            }else if (isMax(columnName)) {
                columnnamefinal=columnName.substring(0, (columnName.length()-3));
                comparator="<=";
            }else if (isMin(columnName)) {
                columnnamefinal=columnName.substring(0, (columnName.length()-3));
                comparator=">=";
            }else{
                columnnamefinal=columnName;
                comparator="LIKE";
            }
            String truncatedColumnName = columnName.replaceAll("(debut|fin)$", "");
            String finalColumnName = isDebut(columnName) || isFin(columnName) ? truncatedColumnName : columnName;
            if (isNumeric(columnValue)) {
                int value = Integer.parseInt(columnValue);
                conditions.add(finalColumnName + " " + comparator + " " + value);
            } else {
                conditions.add(finalColumnName + " " + comparator + " '%" + columnValue + "%'");
            }
        }
    }return String.join(" AND ", conditions);
}

private static boolean isNumeric(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private static boolean isDebut(String str) {
    return str.matches(".*debut$");
}

private static boolean isFin(String str) {
    return str.matches(".*fin$");
}
private static boolean isMax(String str) {
    return str.matches(".*max$");
}

private static boolean isMin(String str) {
    return str.matches(".*min$");
}

}
