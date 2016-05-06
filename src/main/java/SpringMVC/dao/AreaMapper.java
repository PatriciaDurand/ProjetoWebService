package SpringMVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import SpringMVC.model.Area;
import org.springframework.jdbc.core.RowMapper;

public class AreaMapper implements RowMapper<Area> {

    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area = new Area();
        area.setCodigo(rs.getInt(1));
        area.setNome(rs.getString(2));
        return area;
    }

}
