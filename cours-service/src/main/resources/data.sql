-- Cours :
--     • id_cours
--     • nom => theme
--     • description => description_theme
--     • statut = > ACTIF || SUPPRIME
--     • acces = > GRATUIT || PAYANT
--     • prix
--     • []id_episode => ensemble/liste d’épisodes vidéo

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'Docker', 'Docker... Une alternative aux VM !', 'ACTIF', 'GRATUIT', 0);  

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', 'NosQL', 'Les NoSQL, une réponse au Big Data.', 'ACTIF', 'GRATUIT', 0);

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('4346f850-647c-11eb-ae93-0242ac130002', 'UML', 'Un super cours sur la modélisation !', 'SUPPRIME', 'GRATUIT', 0);

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('4a9a0cfa-647c-11eb-ae93-0242ac130002', 'Java-JEE', 'Java JEE n`aura plus de secret pour vous.', 'SUPPRIME', 'PAYANT', 50);

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('4fc174ca-647c-11eb-ae93-0242ac130002', 'Java-Spring', 'JEE, Spring et Maven un aperçue des dépendaces de base.', 'ACTIF', 'PAYANT', 100);

-- Episode/Video : 
--     • id_episode
--     • concept => concept (du cours) qui est présenté
--     • href

-- Docker --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('ddc2ced8-6489-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Introduciton à Docker', 'https://www.youtube.com/watch?v=JSLpG_spOBM', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('c758595a-648a-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Docker Images & Run Containers', 'https://www.youtube.com/watch?v=CcxbHkqzJuI', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('181003d4-648b-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Docker Compose', 'https://www.youtube.com/watch?v=dWcoIxRfs8Y', 'ACTIF');

-- NoSQL --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('6ec2d90e-648b-11eb-ae93-0242ac130002', '3d96e1a4-647c-11eb-ae93-0242ac130002', 'Contexte du Big Data', 'https://www.youtube.com/watch?v=an86433PT8Q', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('53d2c884-648b-11eb-ae93-0242ac130002', '3d96e1a4-647c-11eb-ae93-0242ac130002', 'Introduciton aux NoSQL', 'https://www.youtube.com/watch?v=MlJ6TNyTWXU', 'ACTIF');

-- UML --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('d07eb0cc-648c-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Introduction à l`UML', 'https://www.youtube.com/watch?v=dJd6azZr9Kg', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('d8833806-648c-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Cas d`utilisation - 1. Diagrammes de cas d`utilisation', 'https://www.youtube.com/watch?v=GC5BdRve38A', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('dfaa0858-648c-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Cas d`utilisation - 2. Scénarios détaillés et diagrammes de séquence', 'https://www.youtube.com/watch?v=1G0omjzh1OQ', 'SUPPRIME');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('ac6226f0-648d-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Diagrammes de classes - 1. Classes et associations', 'https://www.youtube.com/watch?v=8VMMu-vcF60', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('19f832b8-648e-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Diagrammes de classes - 2. Associations particulières, héritage', 'https://www.youtube.com/watch?v=nRqTXoiNUHk', 'SUPPRIME');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('24b04650-648e-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Diagrammes de classes - 3. Contraintes', 'https://www.youtube.com/watch?v=a3DWVNWg2Oo', 'SUPPRIME');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('2d6a5326-648e-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'Diagrammes de classes - 4. Opérations', 'https://www.youtube.com/watch?v=RxkarRkq10o', 'SUPPRIME');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('171a8894-648d-11eb-ae93-0242ac130002', '4346f850-647c-11eb-ae93-0242ac130002', 'UML - Diagrammes de séquence (conception)', 'https://www.youtube.com/watch?v=fPm5NrvmXHc', 'ACTIF');

-- Java-JEE --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('90c72502-648e-11eb-ae93-0242ac130002', '4a9a0cfa-647c-11eb-ae93-0242ac130002', 'Java vs Java EE: What`s The Differences?', 'https://www.youtube.com/watch?v=jmLfIZw2WWY', 'SUPPRIME');

-- Java-Spring --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('5acbf4be-64ac-11eb-ae93-0242ac130002', '4fc174ca-647c-11eb-ae93-0242ac130002', 'Débuter avec Spring INTRO', 'https://www.youtube.com/watch?v=UUUtQr-KXOY', 'ACTIF');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('24d20960-64b7-11eb-ae93-0242ac130002', '4fc174ca-647c-11eb-ae93-0242ac130002', 'Spring rest', 'https://www.youtube.com/watch?v=VwD4CsJAYNo', 'ACTIF');
