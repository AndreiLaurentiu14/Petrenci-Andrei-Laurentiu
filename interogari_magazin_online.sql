USE magazin_online;

-- JOIN 1
SELECT p.nume AS produs, p.brand, p.pret, c.nume AS categorie
FROM produse p
JOIN categorii c ON p.id_categorie = c.id_categorie;

-- JOIN 2
SELECT co.id_comanda, cl.nume AS client, co.data_comanda, co.status_comanda
FROM comenzi co
JOIN clienti cl ON co.id_client = cl.id_client;

-- Agregare
SELECT co.id_comanda,
       SUM(dc.cantitate * dc.pret_unitar) AS total_comanda
FROM comenzi co
JOIN detaliicomanda dc ON co.id_comanda = dc.id_comanda
GROUP BY co.id_comanda;

-- Subquery
SELECT nume, pret
FROM produse
WHERE pret > (
    SELECT AVG(pret)
    FROM produse
);

-- View
CREATE OR REPLACE VIEW view_vanzari AS
SELECT co.id_comanda,
       cl.nume AS client,
       SUM(dc.cantitate * dc.pret_unitar) AS total
FROM comenzi co
JOIN clienti cl ON co.id_client = cl.id_client
JOIN detaliicomanda dc ON co.id_comanda = dc.id_comanda
GROUP BY co.id_comanda, cl.nume;

-- Procedura stocata
DELIMITER $$

CREATE PROCEDURE RaportClient(IN idClient INT)
BEGIN
    SELECT c.nume,
           co.id_comanda,
           co.data_comanda
    FROM clienti c
    JOIN comenzi co ON c.id_client = co.id_client
    WHERE c.id_client = idClient;
END $$

DELIMITER ;

-- Trigger
DELIMITER $$

CREATE TRIGGER trg_actualizare_stoc
AFTER INSERT ON detaliicomanda
FOR EACH ROW
BEGIN
    UPDATE stocuri
    SET cantitate = cantitate - NEW.cantitate
    WHERE id_produs = NEW.id_produs;
END $$

DELIMITER ;

-- Indecsi
CREATE INDEX idx_produs_nume ON produse(nume);
CREATE INDEX idx_client_nume ON clienti(nume);
CREATE INDEX idx_comanda_data ON comenzi(data_comanda);