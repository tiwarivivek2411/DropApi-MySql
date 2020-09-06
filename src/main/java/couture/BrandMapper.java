package couture;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import couture.Brand;


public class BrandMapper implements ResultSetMapper<Brand> {
    private static final String ID = "id";
    private static final String NAME = "name";

    public Brand map(int i, ResultSet resultSet, StatementContext statementContext)
            throws SQLException {
        Brand brand = new Brand(resultSet.getInt(ID),resultSet.getString(NAME));
        return brand;
    }
}