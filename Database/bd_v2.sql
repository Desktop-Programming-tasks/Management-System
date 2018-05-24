-- MySQL Script generated by MySQL Workbench
-- qui 24 mai 2018 17:53:02 GMT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema desktop_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `desktop_db` ;

-- -----------------------------------------------------
-- Schema desktop_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `desktop_db` DEFAULT CHARACTER SET utf8 ;
USE `desktop_db` ;

-- -----------------------------------------------------
-- Table `desktop_db`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Person` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Person` (
  `idPerson` VARCHAR(50) NOT NULL,
  `namePerson` VARCHAR(255) NOT NULL,
  `tel1Person` VARCHAR(50) NOT NULL,
  `tel2Person` VARCHAR(50) NULL,
  PRIMARY KEY (`idPerson`),
  UNIQUE INDEX `idPerson_UNIQUE` (`idPerson` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`LegalPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`LegalPerson` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`LegalPerson` (
  `rgLegalPerson` VARCHAR(45) NOT NULL,
  `Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_LegalPerson_Person1_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_LegalPerson_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `desktop_db`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`JuridicalPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`JuridicalPerson` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`JuridicalPerson` (
  `Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_JuridicalPerson_Person_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_JuridicalPerson_Person`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `desktop_db`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`State` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`State` (
  `nameState` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `nameState_UNIQUE` (`nameState` ASC),
  PRIMARY KEY (`nameState`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`City`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`City` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`City` (
  `nameCity` VARCHAR(255) NOT NULL,
  `State_nameState` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`nameCity`, `State_nameState`),
  INDEX `fk_City_State1_idx` (`State_nameState` ASC),
  CONSTRAINT `fk_City_State1`
    FOREIGN KEY (`State_nameState`)
    REFERENCES `desktop_db`.`State` (`nameState`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Address` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Address` (
  `Person_idPerson` VARCHAR(50) NOT NULL,
  `streetAddress` VARCHAR(255) NOT NULL,
  `numberAddress` INT NOT NULL,
  `districtAddress` VARCHAR(45) NULL,
  `City_nameCity` VARCHAR(255) NOT NULL,
  `City_State_nameState` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_Address_City1_idx` (`City_nameCity` ASC, `City_State_nameState` ASC),
  CONSTRAINT `fk_Address_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `desktop_db`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_City1`
    FOREIGN KEY (`City_nameCity` , `City_State_nameState`)
    REFERENCES `desktop_db`.`City` (`nameCity` , `State_nameState`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Supplier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Supplier` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Supplier` (
  `idSupplier` INT NOT NULL AUTO_INCREMENT,
  `nameSupplier` VARCHAR(255) NOT NULL,
  `JuridicalPerson_idJuridicalPerson` INT NOT NULL,
  PRIMARY KEY (`idSupplier`, `JuridicalPerson_idJuridicalPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Brand`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Brand` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Brand` (
  `idBrand` INT NOT NULL AUTO_INCREMENT,
  `nameBrand` VARCHAR(45) NULL,
  PRIMARY KEY (`idBrand`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Supplier_has_Brand`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Supplier_has_Brand` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Supplier_has_Brand` (
  `Supplier_idSupplier` INT NOT NULL,
  `Brand_idBrand` INT NOT NULL,
  PRIMARY KEY (`Supplier_idSupplier`, `Brand_idBrand`),
  INDEX `fk_Supplier_has_Brand_Brand1_idx` (`Brand_idBrand` ASC),
  CONSTRAINT `fk_Supplier_has_Brand_Supplier1`
    FOREIGN KEY (`Supplier_idSupplier`)
    REFERENCES `desktop_db`.`Supplier` (`idSupplier`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Supplier_has_Brand_Brand1`
    FOREIGN KEY (`Brand_idBrand`)
    REFERENCES `desktop_db`.`Brand` (`idBrand`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Product` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Product` (
  `barCodeProduct` VARCHAR(255) NOT NULL,
  `nameProduct` VARCHAR(255) NOT NULL,
  `priceProduct` FLOAT NOT NULL,
  `quantityProduct` INT NOT NULL DEFAULT 0,
  `Brand_idBrand` INT NOT NULL,
  PRIMARY KEY (`barCodeProduct`, `Brand_idBrand`),
  INDEX `fk_Product_Brand1_idx` (`Brand_idBrand` ASC),
  CONSTRAINT `fk_Product_Brand1`
    FOREIGN KEY (`Brand_idBrand`)
    REFERENCES `desktop_db`.`Brand` (`idBrand`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`EmployeeType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`EmployeeType` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`EmployeeType` (
  `idEmployeeType` INT NOT NULL AUTO_INCREMENT,
  `nameEmployeeType` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idEmployeeType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Employee` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Employee` (
  `Person_idPerson` VARCHAR(50) NOT NULL,
  `loginEmployee` VARCHAR(30) NOT NULL,
  `passwordEmployee` VARCHAR(50) NOT NULL,
  `EmployeeType_idEmployeeType` INT NOT NULL,
  PRIMARY KEY (`Person_idPerson`, `EmployeeType_idEmployeeType`),
  INDEX `fk_Employee_EmployeeType1_idx` (`EmployeeType_idEmployeeType` ASC),
  INDEX `fk_Employee_Person1_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_Employee_EmployeeType1`
    FOREIGN KEY (`EmployeeType_idEmployeeType`)
    REFERENCES `desktop_db`.`EmployeeType` (`idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `desktop_db`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Registry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Registry` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Registry` (
  `idRegistry` INT NOT NULL,
  `Employee_LegalPerson_Person_idPerson` INT NOT NULL,
  `dateRegistry` DATE NOT NULL,
  `valueRegistry` FLOAT NOT NULL,
  `typeRegistry` INT NOT NULL,
  `Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idRegistry`, `Employee_LegalPerson_Person_idPerson`),
  INDEX `fk_Registry_Person1_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_Registry_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `desktop_db`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Transaction` (
  `idTransaction` INT NOT NULL,
  `idRegistry_Transaction` INT NOT NULL,
  `valueTransaction` FLOAT NOT NULL,
  PRIMARY KEY (`idTransaction`, `idRegistry_Transaction`),
  INDEX `fk_Transaction_Registry_idx` (`idRegistry_Transaction` ASC),
  CONSTRAINT `fk_Transaction_Registry`
    FOREIGN KEY (`idRegistry_Transaction`)
    REFERENCES `desktop_db`.`Registry` (`idRegistry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`ServiceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`ServiceType` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`ServiceType` (
  `idServiceType` INT NOT NULL AUTO_INCREMENT,
  `nameServiceType` VARCHAR(45) NULL,
  `priceServiceType` VARCHAR(45) NULL,
  PRIMARY KEY (`idServiceType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`ServiceStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`ServiceStatus` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`ServiceStatus` (
  `idServiceStatus` INT NOT NULL AUTO_INCREMENT,
  `nameServiceStatus` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idServiceStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `desktop_db`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desktop_db`.`Service` ;

CREATE TABLE IF NOT EXISTS `desktop_db`.`Service` (
  `idService` INT NOT NULL AUTO_INCREMENT,
  `startDateService` DATE NOT NULL,
  `estimatedEndDateService` DATE NOT NULL,
  `valueService` FLOAT NOT NULL,
  `Employee_EmployeeType_idEmployeeType` INT NOT NULL,
  `ServiceType_idServiceType` INT NOT NULL,
  `ServiceStatus_idServiceStatus` INT NOT NULL,
  PRIMARY KEY (`idService`, `Employee_EmployeeType_idEmployeeType`, `ServiceType_idServiceType`, `ServiceStatus_idServiceStatus`),
  INDEX `fk_Service_Employee1_idx` (`Employee_EmployeeType_idEmployeeType` ASC),
  INDEX `fk_Service_ServiceType1_idx` (`ServiceType_idServiceType` ASC),
  INDEX `fk_Service_ServiceStatus1_idx` (`ServiceStatus_idServiceStatus` ASC),
  CONSTRAINT `fk_Service_Employee1`
    FOREIGN KEY (`Employee_EmployeeType_idEmployeeType`)
    REFERENCES `desktop_db`.`Employee` (`EmployeeType_idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_ServiceType1`
    FOREIGN KEY (`ServiceType_idServiceType`)
    REFERENCES `desktop_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_ServiceStatus1`
    FOREIGN KEY (`ServiceStatus_idServiceStatus`)
    REFERENCES `desktop_db`.`ServiceStatus` (`idServiceStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;