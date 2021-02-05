-- Cours :
--     • id_cours
--     • nom => theme
--     • description => description_theme
--     • statut = > actif || supprime
--     • acces = > gratuit || payant
--     • prix
--     • []id_episode => ensemble/liste d’épisodes vidéo

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('372049f0-647c-11eb-ae93-0242ac130002', 'Docker', 'Docker... Une alternative aux VM !', 'actif', 'gratuit', 0);  

INSERT INTO cours ( id, nom, description, statut, acces, prix) 
VALUES ('3d96e1a4-647c-11eb-ae93-0242ac130002', 'NosQL', 'Les NoSQL, une réponse au Big Data.', 'actif', 'gratuit', 0);

-- Episode/Video : 
--     • id_episode
--     • concept => concept (du cours) qui est présenté
--     • href

-- Docker --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('ddc2ced8-6489-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Introduciton à Docker', 'https://www.youtube.com/watch?v=JSLpG_spOBM', 'actif');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('c758595a-648a-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Docker Images & Run Containers', 'https://www.youtube.com/watch?v=CcxbHkqzJuI', 'actif');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('181003d4-648b-11eb-ae93-0242ac130002', '372049f0-647c-11eb-ae93-0242ac130002', 'Docker Compose', 'https://www.youtube.com/watch?v=dWcoIxRfs8Y', 'actif');

-- NoSQL --
INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('6ec2d90e-648b-11eb-ae93-0242ac130002', '3d96e1a4-647c-11eb-ae93-0242ac130002', 'Contexte du Big Data', 'https://www.youtube.com/watch?v=an86433PT8Q', 'actif');

INSERT INTO episode (id, cours_id, concept, href, statut) 
VALUES ('53d2c884-648b-11eb-ae93-0242ac130002', '3d96e1a4-647c-11eb-ae93-0242ac130002', 'Introduciton aux NoSQL', 'https://www.youtube.com/watch?v=MlJ6TNyTWXU', 'actif');
