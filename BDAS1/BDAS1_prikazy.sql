/*A1 Seznam psů, kteří byli odloženi Petrem Novým*/
SELECT p.id_pes, p.jmeno
FROM psi p
JOIN odlozeni d
ON p.id_pes = d.id_pes
JOIN osoby o
ON d.id_osoba = o.id_osoba
WHERE o.jmeno LIKE 'Petr' AND o.prijmeni LIKE 'Nový';

/*A2 Seznam psů, kteří nebyli odloženi Petrem Novým*/
SELECT p.id_pes, p.jmeno
FROM psi p
JOIN odlozeni d
ON p.id_pes = d.id_pes
JOIN osoby o
ON d.id_osoba = o.id_osoba
WHERE o.jmeno NOT LIKE 'Petr' AND o.prijmeni NOT LIKE 'Nový';

/*A3 Seznam psů, kteří podstoupili procedury pouze v klinice s id 2*/
SELECT DISTINCT p.id_pes, p.jmeno
FROM psi p
JOIN procedury r
ON p.id_pes = r.id_pes
MINUS (SELECT DISTINCT p.id_pes, p.jmeno
FROM psi p
JOIN procedury r
ON p.id_pes = r.id_pes
WHERE id_klinika = 1 OR id_klinika = 2);

/*A4 Seznam psů, kteří byli oštřováni ve všech klinikách*/
SELECT DISTINCT p.id_pes, p.jmeno
FROM psi p
JOIN procedury r 
ON p.id_pes = r.id_pes
WHERE id_klinika = 1 AND id_klinika = 2 AND id_klinika = 3;

/*A5 Seznam osob, které vlastní kliniky a adresy klinik*/
SELECT id_osoba, jmeno, prijmeni, veterinarnikliniky.adresa
FROM osoby
JOIN veterinarnikliniky USING (id_osoba);

/*A6 Seznam klinik a jejich spojených licencí*/
SELECT k.id_klinika, k.adresa, l.id_licence, l.datum_do
FROM veterinarnikliniky k
JOIN licence l
ON l.id_klinika = k.id_klinika;

/*A7 Seznam všech vycházek a jejich spojených psů*/
SELECT *
FROM psi
NATURAL JOIN vychazky;

/*A8 Cross join psů a vycházek*/
SELECT *
FROM psi
CROSS JOIN vychazky;

/*A9 Seznam všech osob včetně jejich přiřazených klinik*/
SELECT o.id_osoba, o.jmeno, o.prijmeni, NVL(k.adresa, 'Žádná klinika')
FROM osoby o
LEFT OUTER JOIN veterinarnikliniky k ON k.id_osoba = o.id_osoba;

/*A10 Seznam osob, které vlastní kliniku*/
SELECT o.id_osoba, o.jmeno, o.prijmeni, NVL(k.adresa, 'Žádná klinika')
FROM osoby o
RIGHT OUTER JOIN veterinarnikliniky k ON k.id_osoba = o.id_osoba;

/*A11 Seznam psů a jejich chování*/
SELECT p.id_pes, p.jmeno, i.id_incident, NVL(i.popis, 'Nic neprovedl')
FROM psi p
FULL OUTER JOIN incidenty i
ON p.id_pes = i.id_pes;

/*A12 Seznam darů, kde byl darovaný obnos vyšší než průměr*/
SELECT id_dar, obnos, ostatni
FROM dary
WHERE obnos > (SELECT AVG(obnos) FROM dary);

/*A13 Seznam nalezených psů*/
SELECT id_pes, jmeno
FROM (SELECT psi.id_pes, jmeno, misto FROM psi
JOIN nalezeni ON psi.id_pes = nalezeni.id_pes);

/*A14 Pocty všech kategorií psů v databázi*/
SELECT DISTINCT (SELECT COUNT(*) FROM nalezeni) AS Pocet_nalezenych,
(SELECT COUNT (*) FROM odchyceni) AS Pocet_odchycenych,
(SELECT COUNT (*) FROM odlozeni) AS Pocet_odlozenych
FROM psi;

/*A15 Seznam osob, které nedarovaly peníze, ale jiné věci*/
SELECT DISTINCT o.jmeno, o.prijmeni
FROM osoby o
JOIN dary d ON o.id_osoba = d.id_osoba
WHERE EXISTS (SELECT id_osoba FROM dary WHERE d.obnos IS NULL);

/*A16 Seznam všech adres z databáze a id osob k nim přiřazených*/
SELECT adresa, id_osoba FROM osoby
UNION
SELECT adresa, id_osoba FROM veterinarnikliniky
ORDER BY ADRESA;

/*A17 Seznam psů, kteří jsou rezervováni a nebyli ještě venčeni*/
SELECT p.id_pes, p.jmeno
FROM psi p
JOIN rezervace r 
ON p.id_pes = r.id_pes
MINUS (SELECT DISTINCT p.id_pes, p.jmeno
FROM psi p
JOIN vychazky v ON p.id_pes = v.id_pes);

/*A18 Seznam psů, kteří způsobili alespoň jeden incident a jsou po smrti*/
SELECT psi.id_pes, psi.jmeno
FROM psi
JOIN incidenty
ON psi.id_pes = incidenty.id_pes
INTERSECT
SELECT psi.id_pes, psi.jmeno
FROM psi
JOIN umrti
ON psi.id_pes = umrti.id_pes;

/*A19 Seznam psů, kteří mají jméno delší jak 5 písmen*/
SELECT *
FROM psi
WHERE LENGTH(jmeno) > 5;

/*A20 Rozdíl součtu všech finančních darů a cen provedených procedur*/
SELECT ABS(x - y) FROM
(SELECT SUM(dary.obnos) AS x FROM dary),
(SELECT SUM(procedury.cena) AS y FROM procedury);

/*A21 Seznam psů, kteří byli nalezeni mezi roky 2010 a 2015*/
SELECT p.id_pes, p.jmeno, n.datum
FROM psi p
JOIN nalezeni n ON p.id_pes = n.id_pes
WHERE n.datum <= TO_DATE('1.1.2015', 'dd-mm-yyyy') AND n.datum >= TO_DATE('1.1.2010', 'dd-mm-yyyy');

/*A22 Pocčet darů, minimální finanční dar, maximální finanční dar, součet všech finančních darů a průměrný finanční dar*/
SELECT COUNT(*), MIN(dary.obnos), MAX(dary.obnos), SUM(dary.obnos), AVG(dary.obnos)
FROM dary;

/*A23 Seznam osob, které venčily psi a kolikrát*/
SELECT o.id_osoba, o.jmeno, o.prijmeni, COUNT(*)
FROM osoby o
JOIN vychazky v ON o.id_osoba = v.id_osoba
GROUP BY o.id_osoba, o.jmeno, o.prijmeni;

/*A24 Seznam osob, které do útulku neuložily psa*/
SELECT DISTINCT o.id_osoba, o.jmeno, o.prijmeni
FROM osoby o
LEFT JOIN odlozeni d ON o.id_osoba = d.id_osoba
WHERE d.id_osoba IS NULL;

SELECT DISTINCT o.id_osoba, o.jmeno, o.prijmeni
FROM osoby o
MINUS(SELECT DISTINCT o.id_osoba, o.jmeno, o.prijmeni
FROM osoby o
LEFT JOIN odlozeni d ON o.id_osoba = d.id_osoba
WHERE d.id_osoba IS NOT NULL);

SELECT DISTINCT o.id_osoba, o.jmeno, o.prijmeni
FROM osoby o
WHERE o.id_osoba NOT IN (SELECT d.id_osoba FROM odlozeni d);

/*A25 Seznam psů a počet podstoupených procedur nad 4*/
SELECT p.id_pes, p.jmeno, COUNT(*)
FROM psi p
JOIN procedury r ON p.id_pes = r.id_pes
WHERE p.id_plemeno = 7
GROUP BY p.id_pes, p.jmeno
HAVING COUNT(*) > 3
ORDER BY COUNT(*) DESC;

/*A26 Seznam všech prohlídek s se všemi psy, adresami a jmén správců klinik*/
CREATE OR REPLACE VIEW moje_view AS
SELECT k.id_klinika AS id_kliniky, k.adresa AS adresa_kliniky, o.jmeno || ' ' || o.prijmeni AS jmeno_spravce, d.popis AS popis_procedury, d.cena AS cena_procedury, p.jmeno AS jmeno_psa FROM veterinarnikliniky k
JOIN osoby o ON k.id_osoba = o.id_osoba
JOIN procedury d ON k.id_klinika = d.id_klinika
JOIN psi p ON d.id_pes = p.id_pes;

/*A27 Výpis seznamu z předchozího view, s podmínkou ceny nad 1000 Kč*/
SELECT * FROM moje_view
WHERE cena_procedury > 1000;

/*A28*/
INSERT INTO rezervace(id_rezervace, datum, id_pes, id_osoba)
SELECT p.id_pes, CURRENT_DATE, p.id_pes, 2
FROM psi p
Where p.jmeno LIKE 'Neznámý';

/*A29 Změna jména psa, který se jmenuje Brok*/
UPDATE psi SET jmeno = 'Neznámý'
WHERE id_pes = (SELECT id_pes FROM psi WHERE jmeno LIKE 'Brok');

/*A30 Smazání psa, který se jmenuje Odstranit*/
DELETE psi WHERE id_pes = (SELECT id_pes FROM psi WHERE jmeno LIKE 'Odstranit');
