package ru.reso.resocalc.Utils.Factories.ConcreteFactories;

        import ru.reso.resocalc.Entity.*;
        import ru.reso.resocalc.Utils.DAOUtils;
        import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
        import ru.reso.resocalc.Utils.sqlLogging;

        import javax.sql.rowset.WebRowSet;
        import java.sql.SQLException;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import static ru.reso.resocalc.Utils.DAOUtils.dateToString;
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
            Logger.getLogger("").log(Level.SEVERE, key + " ->  " + value, "Инфо");
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

        String result = String.valueOf(rs.getLong("ID"))
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
                + "-" + rs.getString("VIPCLIENT");


        return result;
    }

    private String rowsToKeyString(Integer id, WebRowSet rs) throws SQLException {


        String result = String.valueOf(rs.getLong("CALCID"))
                + "-" + String.valueOf(rs.getInt("PARTHNER_TYPE"));


        return result;
    }

}
