package smart;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import smart.ElecUsage;
import smart.ResCredential;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-27T18:52:25")
@StaticMetamodel(Resident.class)
public class Resident_ { 

    public static volatile SingularAttribute<Resident, String> address;
    public static volatile CollectionAttribute<Resident, ResCredential> resCredentialCollection;
    public static volatile SingularAttribute<Resident, String> fName;
    public static volatile CollectionAttribute<Resident, ElecUsage> elecUsageCollection;
    public static volatile SingularAttribute<Resident, Date> dob;
    public static volatile SingularAttribute<Resident, String> sName;
    public static volatile SingularAttribute<Resident, String> energyProvider;
    public static volatile SingularAttribute<Resident, String> postcode;
    public static volatile SingularAttribute<Resident, String> mobile;
    public static volatile SingularAttribute<Resident, Integer> noOfRes;
    public static volatile SingularAttribute<Resident, Integer> resid;
    public static volatile SingularAttribute<Resident, String> email;

}