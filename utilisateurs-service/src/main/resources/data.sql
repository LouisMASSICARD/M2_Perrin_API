-- Utilisateur :
--    • id_user
--    • nom
--    • prenom
--    • mail  => identification
--    • statut => actif || supprime
INSERT INTO utilisateur ( id, nom, prenom, mail, statut) 
VALUES ('de7d9052-4961-4b4f-938a-3cd12cbe1f82', 'MASSICARD', 'Louis', 'louis@netlor.fr','actif');  

INSERT INTO utilisateur ( id, nom, prenom, mail, statut) 
VALUES ('425e7701-02c6-4de3-9333-a2459eece1c8', 'ROMANEL', 'Claire', 'claire@netlor.fr','actif');

INSERT INTO utilisateur ( id, nom, prenom, mail, statut) 
VALUES ('9de8a2f0-5e45-11eb-ae93-0242ac130002', 'DELAROUE', 'Antoine', 'antoine@netlor.fr','actif');

INSERT INTO utilisateur ( id, nom, prenom, mail, statut) 
VALUES ('d7a4a458-5e45-11eb-ae93-0242ac130002', 'CHARLES', 'Anne-Laure', 'anne-laure@netlor.fr','supprime');

INSERT INTO utilisateur ( id, nom, prenom, mail, statut) 
VALUES ('f71b0070-5e45-11eb-ae93-0242ac130002', 'YACIA', 'Adel', 'adel@netlor.fr','supprime');

-- Abonnement (cours-video) :
--    • id_user
--    • id_cours
--    • numero_CB
--    • code
--    • code_de_validation
--    • consulte => visonné || non_ visonné
-- Louis & Big data --
-- INSERT INTO cours_intervenants_id VALUES ('de7d9052-4961-4b4f-938a-3cd12cbe1f82', 'f857ee10-5e47-11eb-ae93-0242ac130002');
-- Louis & Sécurité --
-- INSERT INTO cours_intervenants_id VALUES ('de7d9052-4961-4b4f-938a-3cd12cbe1f82', '192c6d64-5e48-11eb-ae93-0242ac130002');

-- Claire & Sécurité --
-- INSERT INTO cours_intervenants_id VALUES ('425e7701-02c6-4de3-9333-a2459eece1c8', '192c6d64-5e48-11eb-ae93-0242ac130002');

-- Anne-Laure é Big data --
-- INSERT INTO cours_intervenants_id VALUES ('d7a4a458-5e45-11eb-ae93-0242ac130002', 'f857ee10-5e47-11eb-ae93-0242ac130002');