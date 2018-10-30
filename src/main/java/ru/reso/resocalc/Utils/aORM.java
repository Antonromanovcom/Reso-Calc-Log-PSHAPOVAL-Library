package ru.reso.resocalc.Utils;

import ru.reso.resocalc.Entity.WsCalcLogsNew;
import ru.reso.wp.srv.db.models.StmtParamList;

public interface aORM {

    StmtParamList paramListGenerator2(String aSQL, WsCalcLogsNew wsCalcLogsNew, Long calcid);
}
