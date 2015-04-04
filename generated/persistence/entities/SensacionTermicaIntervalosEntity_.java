package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.SensacionTermicaEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(SensacionTermicaIntervalosEntity.class)
public class SensacionTermicaIntervalosEntity_ { 

    public static volatile SingularAttribute<SensacionTermicaIntervalosEntity, SensacionTermicaEntity> sensacionTermica;
    public static volatile SingularAttribute<SensacionTermicaIntervalosEntity, Date> fecha;
    public static volatile SingularAttribute<SensacionTermicaIntervalosEntity, Integer> codigo;
    public static volatile SingularAttribute<SensacionTermicaIntervalosEntity, String> hora;
    public static volatile SingularAttribute<SensacionTermicaIntervalosEntity, Integer> valorHora;

}