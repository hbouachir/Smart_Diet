<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20211202160719 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D1CD53A10 FOREIGN KEY (user_app_id) REFERENCES user_app (id)');
        $this->addSql('CREATE INDEX IDX_6EEAA67D1CD53A10 ON commande (user_app_id)');
        $this->addSql('ALTER TABLE commande RENAME INDEX fk_6eeaa67da76ed395 TO IDX_6EEAA67DA76ED395');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D1CD53A10');
        $this->addSql('DROP INDEX IDX_6EEAA67D1CD53A10 ON commande');
        $this->addSql('ALTER TABLE commande RENAME INDEX idx_6eeaa67da76ed395 TO FK_6EEAA67DA76ED395');
    }
}
