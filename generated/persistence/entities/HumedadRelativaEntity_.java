package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.HumedadRelativaIntervalosEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(HumedadRelativaEntity.class)
public class HumedadRelativaEntity_ { 

    public static volatile SingularAttribute<HumedadRelativaEntity, Integer> minima;
    public static volatile SingularAttribute<HumedadRelativaEntity, Date> fecha;
    public static volatile SingularAttribute<HumedadRelativaEntity, Integer> codigo;
    public static volatile ListAttribute<HumedadRelativaEntity, HumedadRelativaIntervalosEntity> humedadIntervalos;
    public static volatile SingularAttribute<HumedadRelativaEntity, Integer> maxima;

}