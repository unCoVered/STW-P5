package persistence.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.entities.CotaNieveProvEntity;
import persistence.entities.EstadoCieloEntity;
import persistence.entities.HumedadRelativaEntity;
import persistence.entities.ProbPrecipitacionEntity;
import persistence.entities.RachaMaxEntity;
import persistence.entities.SensacionTermicaEntity;
import persistence.entities.TemperaturaEntity;
import persistence.entities.VientoEntity;

@Generated(value="EclipseLink-2.6.0.v20150309-rNA", date="2015-04-04T13:38:53")
@StaticMetamodel(DiaEntity.class)
public class DiaEntity_ { 

    public static volatile SingularAttribute<DiaEntity, SensacionTermicaEntity> sensacionTermica;
    public static volatile SingularAttribute<DiaEntity, Date> fecha;
    public static volatile SingularAttribute<DiaEntity, Integer> codigo;
    public static volatile SingularAttribute<DiaEntity, Integer> uvMax;
    public static volatile SingularAttribute<DiaEntity, VientoEntity> viento;
    public static volatile SingularAttribute<DiaEntity, ProbPrecipitacionEntity> probPrecipitacion;
    public static volatile SingularAttribute<DiaEntity, HumedadRelativaEntity> humedadRelativa;
    public static volatile SingularAttribute<DiaEntity, CotaNieveProvEntity> cotaNieveProv;
    public static volatile SingularAttribute<DiaEntity, EstadoCieloEntity> estadoCielo;
    public static volatile SingularAttribute<DiaEntity, TemperaturaEntity> temperatura;
    public static volatile SingularAttribute<DiaEntity, RachaMaxEntity> rachaMax;

}