package ru.reso.resocalc.Utils.Factories.ConcreteFactories;

        import ru.reso.resocalc.Entity.*;
        import ru.reso.resocalc.Utils.DAOUtils;
        import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
        import ru.reso.resocalc.Utils.sqlLogging;

        import javax.sql.rowset.WebRowSet;
        import java.sql.SQLException;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import static ru.reso.resocalc.Utils.DAOUtils.dateToString;
        import static ru.reso.resocalc.Utils.DAOUtils.getJSONfromMap;
        import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet;

public class PartnerFactory implements EntitiesUtils {
    @Override
    public CalcEntity getEntityByCalcId(long calcid) {
        WsPartner partners = null;

        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_PARTNERS, calcid);
        partners = webRowSet2Entity(rs);

        return partners;
    }

    @Override
    public WsPartner webRowSet2Entity(WebRowSet rs) {
        Integer temporary = 1;
        WsPartner partners = new WsPartner();

        if (rs == null) {
            return partners;
        }

        try {
            int count = rs.getMetaData().getColumnCount();

            Logger.getLogger("").log(Level.SEVERE, "Количество записей у Партнеров - " + rs.size(), "Count");
            while (rs.next()) {

                PartnerUnit partnerUnit = new PartnerUnit();
                parseWebRowSet(count, rs, new PartnerUnit(), partnerUnit);
                partners.getPartnerList().add(partnerUnit);
                partners.addToHash(this.rowsToKeyString(temporary, rs), this.rowsToString(rs));
                temporary += 1;
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to convert webRowSet to Entity", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", ex);
            }
        }


        for (String name : partners.getHash().keySet()) {

            String key = name;
            String value = partners.getHash().get(name);
     //       Logger.getLogger("").log(Level.SEVERE, key + " ->  " + value, "Инфо");
        }
        return partners;
    }

    @Override
    public Integer getReordsCount(String sql, long calcid) {
        return null;
    }

    @Override
    public CalcEntity checkNonMatching(long calcid1st, long calcid2d) {
        return null;
    }

    private String rowsToString(WebRowSet rs) throws SQLException {

     /*   String result = String.valueOf(rs.getLong("ID"))
                + "-" + String.valueOf(rs.getInt("TYPE"))
                + "-" + rs.getString("ISRESIDENT")
                + "-" + rs.getString("NAME")
                + "-" + dateToString(rs.getDate("BIRTHDATE"))
                + "-" + rs.getString("INN")
                + "-" + String.valueOf(rs.getInt("DOCTYPE"))
                + "-" + rs.getString("DOCSERIA")
                + "-" + rs.getString("DOCNUMBER")
                + "-" + dateToString(rs.getDate("DOCISSUEDATE"))
                + "-" + rs.getString("ADDRKLADR")
                + "-" + rs.getString("VIPCLIENT"); */


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("ID", String.valueOf(rs.getLong("ID")));
        map.put("TYPE", String.valueOf(rs.getInt("TYPE")));
        map.put("ISRESIDENT", rs.getString("ISRESIDENT"));
        map.put("NAME", rs.getString("NAME"));
        map.put("BIRTHDATE", dateToString(rs.getDate("BIRTHDATE")));
        map.put("LICENCESERIA", rs.getString("INN"));
        map.put("DOCTYPE", String.valueOf(rs.getInt("DOCTYPE")));
        map.put("DOCSERIA", rs.getString("DOCSERIA"));
        map.put("DOCNUMBER", rs.getString("DOCNUMBER"));
        map.put("DOCISSUEDATE", dateToString(rs.getDate("DOCISSUEDATE")));
        map.put("ADDRKLADR", rs.getString("ADDRKLADR"));
        map.put("VIPCLIENT", rs.getString("VIPCLIENT"));


        String result = getJSONfromMap(map);


        return result;
    }

    private String rowsToKeyString(Integer id, WebRowSet rs) throws SQLException {


        String result = String.valueOf(rs.getLong("CALCID"))
                + "-" + String.valueOf(rs.getInt("PARTHNER_TYPE"));


        return result;
    }

}
