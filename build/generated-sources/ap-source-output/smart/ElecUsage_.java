package smart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import smart.Resident;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-27T18:52:25")
@StaticMetamodel(ElecUsage.class)
public class ElecUsage_ { 

    public static volatile SingularAttribute<ElecUsage, Integer> usageid;
    public static volatile SingularAttribute<ElecUsage, Date> uDate;
    public static volatile SingularAttribute<ElecUsage, Double> fridgeUsage;
    public static volatile SingularAttribute<ElecUsage, Double> washmaUsage;
    public static volatile SingularAttribute<ElecUsage, Double> airconUsage;
    public static volatile SingularAttribute<ElecUsage, Integer> temperature;
    public static volatile SingularAttribute<ElecUsage, Integer> uHour;
    public static volatile SingularAttribute<ElecUsage, Resident> resid;

}