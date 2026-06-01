-- magazin_online.categorii definition

create table `categorii` (
  `id_categorie` int(11) not null auto_increment,
`nume` varchar(100) not null,
`descriere` varchar(255) default null,
primary key (`id_categorie`),
unique key `nume` (`nume`)
) engine = InnoDB auto_increment = 20 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.clienti definition

create table `clienti` (
  `id_client` int(11) not null auto_increment,
`nume` varchar(100) not null,
`email` varchar(100) not null,
`telefon` varchar(20) default null,
`adresa` varchar(200) default null,
`oras` varchar(100) default null,
`data_inregistrare` date not null,
primary key (`id_client`),
unique key `email` (`email`),
key `idx_clienti_email` (`email`),
key `idx_client_nume` (`nume`)
) engine = InnoDB auto_increment = 5 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.metodeplata definition

create table `metodeplata` (
  `id_metoda_plata` int(11) not null auto_increment,
`denumire` varchar(50) not null,
primary key (`id_metoda_plata`),
unique key `denumire` (`denumire`)
) engine = InnoDB auto_increment = 4 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.comenzi definition

create table `comenzi` (
  `id_comanda` int(11) not null auto_increment,
`id_client` int(11) not null,
`data_comanda` datetime not null,
`status_comanda` varchar(50) not null,
`id_metoda_plata` int(11) not null,
`total` decimal(10, 2) not null default 0.00,
primary key (`id_comanda`),
key `fk_comenzi_clienti` (`id_client`),
key `fk_comenzi_metode_plata` (`id_metoda_plata`),
key `idx_comenzi_data` (`data_comanda`),
key `idx_comanda_data` (`data_comanda`),
constraint `fk_comenzi_clienti` foreign key (`id_client`) references `clienti` (`id_client`),
constraint `fk_comenzi_metode_plata` foreign key (`id_metoda_plata`) references `metodeplata` (`id_metoda_plata`)
) engine = InnoDB auto_increment = 5 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.produse definition

create table `produse` (
  `id_produs` int(11) not null auto_increment,
`nume` varchar(150) not null,
`descriere` text default null,
`pret` decimal(10, 2) not null,
`id_categorie` int(11) not null,
`brand` varchar(100) default null,
`data_adaugare` date not null,
`activ` tinyint(1) not null default 1,
primary key (`id_produs`),
key `fk_produse_categorii` (`id_categorie`),
key `idx_produse_nume` (`nume`),
key `idx_produs_nume` (`nume`),
constraint `fk_produse_categorii` foreign key (`id_categorie`) references `categorii` (`id_categorie`)
) engine = InnoDB auto_increment = 12 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.recenzii definition

create table `recenzii` (
  `id_recenzie` int(11) not null auto_increment,
`id_client` int(11) not null,
`id_produs` int(11) not null,
`rating` int(11) not null check (`rating` between 1 and 5),
`comentariu` varchar(255) default null,
`data_recenzie` date not null,
primary key (`id_recenzie`),
key `fk_recenzii_clienti` (`id_client`),
key `fk_recenzii_produse` (`id_produs`),
constraint `fk_recenzii_clienti` foreign key (`id_client`) references `clienti` (`id_client`),
constraint `fk_recenzii_produse` foreign key (`id_produs`) references `produse` (`id_produs`)
) engine = InnoDB auto_increment = 5 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.retururi definition

create table `retururi` (
  `id_retur` int(11) not null auto_increment,
`id_comanda` int(11) not null,
`data_retur` date not null,
`motiv` varchar(255) default null,
`suma_returnata` decimal(10, 2) not null,
primary key (`id_retur`),
key `fk_retururi_comenzi` (`id_comanda`),
constraint `fk_retururi_comenzi` foreign key (`id_comanda`) references `comenzi` (`id_comanda`)
) engine = InnoDB auto_increment = 2 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.stocuri definition

create table `stocuri` (
  `id_stoc` int(11) not null auto_increment,
`id_produs` int(11) not null,
`cantitate` int(11) not null default 0,
`ultimul_update` timestamp not null default current_timestamp() on
update
    current_timestamp(),
    primary key (`id_stoc`),
    unique key `id_produs` (`id_produs`),
    constraint `fk_stocuri_produse` foreign key (`id_produs`) references `produse` (`id_produs`)
) engine = InnoDB auto_increment = 7 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
-- magazin_online.detaliicomanda definition

create table `detaliicomanda` (
  `id_detaliu` int(11) not null auto_increment,
`id_comanda` int(11) not null,
`id_produs` int(11) not null,
`cantitate` int(11) not null,
`pret_unitar` decimal(10, 2) not null,
`subtotal` decimal(10, 2) not null,
primary key (`id_detaliu`),
key `fk_detalii_comanda` (`id_comanda`),
key `fk_detalii_produs` (`id_produs`),
constraint `fk_detalii_comanda` foreign key (`id_comanda`) references `comenzi` (`id_comanda`),
constraint `fk_detalii_produs` foreign key (`id_produs`) references `produse` (`id_produs`)
) engine = InnoDB auto_increment = 9 default CHARSET = utf8mb4 collate = utf8mb4_general_ci;
