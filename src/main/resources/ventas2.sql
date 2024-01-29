-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ventas2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ventas2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ventas2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ventas2` ;

-- -----------------------------------------------------
-- Table `ventas2`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas2`.`cliente` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido1` VARCHAR(100) NOT NULL,
  `apellido2` VARCHAR(100) NULL DEFAULT NULL,
  `ciudad` VARCHAR(100) NULL DEFAULT NULL,
  `categoría` INT UNSIGNED NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ventas2`.`comercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas2`.`comercial` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido1` VARCHAR(100) NOT NULL,
  `apellido2` VARCHAR(100) NULL DEFAULT NULL,
  `comisión` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ventas2`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas2`.`pedido` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `total` DOUBLE NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `id_cliente` INT UNSIGNED NOT NULL,
  `id_comercial` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_cliente` (`id_cliente` ASC) VISIBLE,
  INDEX `id_comercial` (`id_comercial` ASC) VISIBLE,
  CONSTRAINT `pedido_ibfk_1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `ventas2`.`cliente` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `pedido_ibfk_2`
    FOREIGN KEY (`id_comercial`)
    REFERENCES `ventas2`.`comercial` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ventas2`.`cliente_has_comercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas2`.`cliente_has_comercial` (
  `cliente_id` INT UNSIGNED NOT NULL,
  `comercial_id` INT UNSIGNED NOT NULL,
  `prioridad` INT NOT NULL,
  `fecha_asociacion` DATE NOT NULL,
  PRIMARY KEY (`cliente_id`, `comercial_id`),
  INDEX `fk_cliente_has_comercial_comercial1_idx` (`comercial_id` ASC) VISIBLE,
  INDEX `fk_cliente_has_comercial_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_has_comercial_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ventas2`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_comercial_comercial1`
    FOREIGN KEY (`comercial_id`)
    REFERENCES `ventas2`.`comercial` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
