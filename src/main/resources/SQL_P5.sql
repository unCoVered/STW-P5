create table prob_precipitacion(
	codigo					integer(10) primary key not null auto_increment, 
  fecha	  				date not null,
  periodo					varchar(5) not null,
  valor_periodo		integer(3)
);

create table cota_nieve_prov(
	codigo					integer(10) primary key not null auto_increment,
  fecha		  			date not null,
  periodo		  		varchar(5) not null,
  descripcion  	  varchar(100)
);
    
create table estado_cielo(
	codigo					integer(10) primary key not null auto_increment,  
  fecha					  date not null,
  periodo				  varchar(5) not null,
  valor_periodo		integer(4)
);

create table viento(
	codigo					integer(10) primary key not null auto_increment, 
  fecha 					date not null,
  periodo					varchar(5) not null,
  direccion				varchar(2),
  velocidad				integer(3)
);

create table racha_max(
	codigo 					integer(10) primary key not null auto_increment, 
  fecha			  		date not null,
	periodo					varchar(5) not null,
  valor_periodo		integer(3)
);

create table temperatura_intervalos(
	codigo					integer(10) primary key not null auto_increment,
  fecha					  date not null,
  hora					  varchar(2) not null,
  valor_hora			integer(2),
  fk_temperatura  integer(10) not null,
  foreign key (fk_temperatura) references temperatura(codigo)
);

create table temperatura(
	codigo					integer(10) primary key not null auto_increment,
  fecha		  			date not null,
  maxima					integer(2) not null,
  minima					integer(2) not null
);

create table sensacion_termica_intervalos(
	codigo					integer(10) primary key not null auto_increment,
  fecha 					date not null,
  hora  					varchar(2) not null,
  valor_hora			integer(2),
  fk_sensacion_termica integer(10) not null,
  foreign key (fk_sensacion_termica) references sensacion_termica(codigo)
);

create table sensacion_termica(
	codigo					integer(10) primary key not null auto_increment,
  fecha					  date not null,
  maxima					integer(2) not null,
  minima					integer(2) not null
);

create table humedad_relativa_intervalos(
	codigo					integer(10) primary key not null auto_increment,
  fecha	  				date not null,
  hora	  				varchar(2) not null,
  valor_hora				integer(2),
  fk_humedad_relativa integer(10) not null,
  foreign key  (fk_humedad_relativa) references humedad_relativa(codigo)
);

create table humedad_relativa(
	codigo					integer(10) primary key not null auto_increment,
  fecha 					date not null,
  maxima					integer(2) not null,
  minima 					integer(2) not null
);

create table dia(
	codigo					integer(10) primary key not null auto_increment, 
  fecha 					date not null,
  fk_prob_precipitacion	integer(10) not null,
  fk_cota_nieve_prov		integer(10) not null,
  fk_estado_cielo 			integer(10) not null,
  fk_viento				integer(10) not null,
	fk_racha_max		integer(10) not null,
  fk_temperatura	integer(10) not null,
  fk_sensacion_termica	integer(10) not null,
  fk_humedad_relativa		integer(10) not null,
  uv_max					integer(2) not null,
  foreign key (fk_prob_precipitacion) references prob_precipitacion(codigo),
  foreign key (fk_cota_nieve_prov) 	references cota_nieve_prov(codigo),
	foreign key (fk_estado_cielo)		references estado_cielo(codigo),
  foreign key (fk_viento) 			references viento(codigo),
  foreign key (fk_racha_max) 			references racha_max(codigo),
  foreign key (fk_temperatura)		references temperatura(codigo),
	foreign key (fk_sensacion_termica) 	references sensacion_termica(codigo),
  foreign key (fk_humedad_relativa) 	references humedad_relativa(codigo)
);
