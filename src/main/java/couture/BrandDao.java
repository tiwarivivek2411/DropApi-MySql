package couture;

import couture.Brand;
import couture.BrandMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(BrandMapper.class)
public abstract class BrandDao {
    // public abstract BrandDao brandDao();

    @SqlQuery("select * from Brand;")
    public abstract List<Brand> getBrands();

    @SqlQuery("select * from Brand where id = :id")
    public abstract Brand getBrand(@Bind("id") final int id);

    @SqlUpdate("insert into Brand(id, name) values(:id, :name)")
    abstract void createBrand(@Bind("id") final int id, @Bind("name") final String name);

    // @SqlUpdate("update employee set name = coalesce(:name, name), " +
    //         " department = coalesce(:department, department), salary = coalesce(:salary, salary)" +
    //         " where id = :id")
    // void editBrand(@BindBean final Employee employee);

    @SqlUpdate("delete from Brand where id = :id")
    abstract  int deleteBrand(@Bind("id") final int id);

    @SqlQuery("select last_insert_id();")
    abstract public int lastInsertId();
}