-- Utilisateur :
--    • id_user
--    • nom
--    • prenom
--    • mail  => identification
--    • statut => ACTIF || SUPPRIME

INSERT INTO utilisateur (id, nom, prenom, mail, statut) 
VALUES ('de7d9052-4961-4b4f-938a-3cd12cbe1f82', 'MASSICARD', 'Louis', 'louis@netlor.fr','ACTIF');  

INSERT INTO utilisateur (id, nom, prenom, mail, statut) 
VALUES ('425e7701-02c6-4de3-9333-a2459eece1c8', 'ROMANEL', 'Claire', 'claire@netlor.fr','ACTIF');

INSERT INTO utilisateur (id, nom, prenom, mail, statut) 
VALUES ('9de8a2f0-5e45-11eb-ae93-0242ac130002', 'DELAROUE', 'Antoine', 'antoine@netlor.fr','ACTIF');

INSERT INTO utilisateur (id, nom, prenom, mail, statut) 
VALUES ('d7a4a458-5e45-11eb-ae93-0242ac130002', 'CHARLES', 'Anne-Laure', 'anne-laure@netlor.fr','SUPPRIME');

INSERT INTO utilisateur (id, nom, prenom, mail, statut) 
VALUES ('f71b0070-5e45-11eb-ae93-0242ac130002', 'YACIA', 'Adel', 'adel@netlor.fr','SUPPRIME');

-- Abonnement (cours-video) :
--    • utilisateur_id
--    • cours_id
--    • statut => ACTIF || SUPPRIME

--    • numero_CB
--    • code
--    • code_de_validation
--    • consulte => visonné || non_ visonné

-- Louis & Docker --
INSERT INTO abonnement (id, utilisateur_id, cours_id, statut) 
VALUES ('1', 'de7d9052-4961-4b4f-938a-3cd12cbe1f82', '372049f0-647c-11eb-ae93-0242ac130002', 'ACTIF');
-- Louis & NosQL --
INSERT INTO abonnement (id, utilisateur_id, cours_id, statut) 
VALUES ('2', 'de7d9052-4961-4b4f-938a-3cd12cbe1f82', '3d96e1a4-647c-11eb-ae93-0242ac130002', 'SUPPRIME');

-- Claire & Docker --
INSERT INTO abonnement (id, utilisateur_id, cours_id, statut) 
VALUES ('3', '425e7701-02c6-4de3-9333-a2459eece1c8', '372049f0-647c-11eb-ae93-0242ac130002', 'ACTIF');

-- Anne-Laure & Java-Spring --
INSERT INTO abonnement (id, utilisateur_id, cours_id, statut) 
VALUES ('4', 'd7a4a458-5e45-11eb-ae93-0242ac130002', '4fc174ca-647c-11eb-ae93-0242ac130002', 'ACTIF');