<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211119005543 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande CHANGE idClient idClient INT DEFAULT NULL');
        $this->addSql('ALTER TABLE consultation CHANGE idRdv idRdv INT DEFAULT NULL');
        $this->addSql('ALTER TABLE facture CHANGE idCommande idCommande INT DEFAULT NULL');
        $this->addSql('ALTER TABLE lignecommande CHANGE idCommande idCommande INT DEFAULT NULL, CHANGE codeProduit codeProduit INT DEFAULT NULL');
        $this->addSql('ALTER TABLE livraison CHANGE idLivreur idLivreur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE payment CHANGE idFacture idFacture INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation CHANGE idPersonne idPersonne INT DEFAULT NULL');
        $this->addSql('ALTER TABLE rendezvous CHANGE idPatient idPatient INT DEFAULT NULL');
        $this->addSql('ALTER TABLE suivi CHANGE idUser idUser INT DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande CHANGE idClient idClient INT NOT NULL');
        $this->addSql('ALTER TABLE consultation CHANGE idRdv idRdv INT NOT NULL');
        $this->addSql('ALTER TABLE facture CHANGE idCommande idCommande INT NOT NULL');
        $this->addSql('ALTER TABLE lignecommande CHANGE codeProduit codeProduit INT NOT NULL, CHANGE idCommande idCommande INT NOT NULL');
        $this->addSql('ALTER TABLE livraison CHANGE idLivreur idLivreur INT NOT NULL');
        $this->addSql('ALTER TABLE payment CHANGE idFacture idFacture INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation CHANGE idPersonne idPersonne INT NOT NULL');
        $this->addSql('ALTER TABLE rendezvous CHANGE idPatient idPatient INT NOT NULL');
        $this->addSql('ALTER TABLE suivi CHANGE idUser idUser INT NOT NULL');
    }
}
