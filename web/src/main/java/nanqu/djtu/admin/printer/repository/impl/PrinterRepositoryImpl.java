package nanqu.djtu.admin.printer.repository.impl;

import nanqu.djtu.admin.printer.repository.PrinterRepositoryI;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.Printer;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
@Repository
public class PrinterRepositoryImpl implements PrinterRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(PrinterRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询打印机信息列表
     *
     * @return 所有未删除打印机的信息
     */
    @Override
    public List<Printer> query4List() {
        String sql = "SELECT printerId,printerZHCNName,printerNumber,printIp FROM baoxiu.baoxiu_printer where deleteFlag=0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new Query4ListRowMapper());
        }catch (Exception e){

            LOG.error("[Printer] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }


    private class Query4ListRowMapper implements RowMapper<Printer> {

        @Override
        public Printer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Printer printer = new Printer();

            printer.setPrinterId(rs.getString("printerId"));
            printer.setPrinterZHCNName(rs.getString("printerZHCNName"));
            printer.setPrinterNumber(rs.getString("printerNumber"));
            printer.setPrintIp(rs.getString("printIp"));

            return printer;
        }
    }

    /**
     * 添加新的打印机
     *
     * @param printer 新校区的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewPrinter(Printer printer) {
        String sql = "INSERT INTO baoxiu.baoxiu_printer (printerId,printerZHCNName,printerNumber,printIp,deleteFlag) VALUES (?,?,?,?,0);";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                printer.getPrinterZHCNName(),
                printer.getPrinterNumber(),
                printer.getPrintIp()
        };

        try {

            return jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {
            LOG.error("[Printer] add new printer  error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 删除打印机
     *
     * @param printerId 打印机Id
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean deletePrinter(String printerId) {
        String sql = "UPDATE baoxiu.baoxiu_printer SET deleteFlag = 1 WHERE printerId =? AND deleteFlag = 0;";
        Object[] args = {printerId};

        try {
            return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {
            LOG.error("[Printer] delete printer  error with info {}.", e.getMessage());
            return  false;
        }
    }

    /**
     * 查询需要编辑的打印机的信息
     *
     * @param printerId 打印机Id
     * @return 校区信息对象
     */
    @Override
    public Printer query4Edit(String printerId) {
        String sql = "SELECT printerId,printerZHCNName,printerNumber,printIp FROM baoxiu.baoxiu_printer where printerId = ? and deleteFlag = 0";
        Object[] args = {printerId};

        try {
            return jdbcTemplate.queryForObject(sql, args, new Query4ListRowMapper());
        }catch (Exception e) {

            LOG.error("[Printer] select printer  error with info {}.", e.getMessage());
            return null;

        }
    }

    /**
     * 更新打印机信息
     *
     * @param printer 新的更改后的打印机信息
     * @return 更改成功返回true, else false
     */
    @Override
    public boolean updatePrinter(Printer printer) {
        String sql = "UPDATE baoxiu.baoxiu_printer SET printerZHCNName = ? ,printerNumber = ?,printIp = ? where deleteFlag = 0 and printerId = ?";
        Object[] args = {
                printer.getPrinterZHCNName(),
                printer.getPrinterNumber(),
                printer.getPrintIp(),
                printer.getPrinterId()};

        try {
            return  jdbcTemplate.update(sql,args) == 1 ;
        }catch (Exception e) {
            LOG.error("[Printer] update printer  error with info {}.", e.getMessage());
            return false;
        }
    }

    /**
     * 验证打印机编号的唯一
     *
     * @param printerNumber 打印机编号
     * @return 唯一返回true, else false
     */

    @Override
    public boolean select4PrinterNumberUnique(String printerNumber) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu.baoxiu_printer WHERE printerNumber = ?";
        Object[] args = {printerNumber};

        try {
            return jdbcTemplate.queryForObject(sql,args,Integer.class) == 0;
        }catch (Exception e) {
            return false;

        }
    }

    @Override
    public boolean query4PrintIpUnique(String printIp) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu.baoxiu_printer WHERE printIp = ?";
        Object[] args = {printIp};

        try {
            return jdbcTemplate.queryForObject(sql,args,Integer.class) == 0;
        }catch (Exception e) {
            return false;

        }
    }


}
