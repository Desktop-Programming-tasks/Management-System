-- MySQL Script generated by MySQL Workbench
-- dom 17 jun 2018 18:52:13 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema management-sys
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema management-sys
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `management-sys` DEFAULT CHARACTER SET utf8 ;
USE `management-sys` ;

-- -----------------------------------------------------
-- Table `management-sys`.`State`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`State` (
  `nameState` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`nameState`),
  UNIQUE INDEX `nameState_UNIQUE` (`nameState` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`City` (
  `nameCity` VARCHAR(255) NOT NULL,
  `State_nameState` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`nameCity`, `State_nameState`),
  INDEX `fk_City_State1_idx` (`State_nameState` ASC),
  CONSTRAINT `fk_City_State1`
    FOREIGN KEY (`State_nameState`)
    REFERENCES `management-sys`.`State` (`nameState`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `idDocumentPerson` VARCHAR(50) NOT NULL,
  `namePerson` VARCHAR(255) NOT NULL,
  `tel1Person` VARCHAR(50) NOT NULL,
  `tel2Person` VARCHAR(50) NULL DEFAULT NULL,
  `isActivePerson` TINYINT(1) NOT NULL,
  UNIQUE INDEX `idPerson_UNIQUE` (`idDocumentPerson` ASC),
  PRIMARY KEY (`idPerson`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Address` (
  `Person_idPerson` VARCHAR(50) NOT NULL,
  `streetAddress` VARCHAR(255) NOT NULL,
  `numberAddress` INT(11) NOT NULL,
  `districtAddress` VARCHAR(45) NULL DEFAULT NULL,
  `City_nameCity` VARCHAR(255) NOT NULL,
  `City_State_nameState` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_Address_City1_idx` (`City_nameCity` ASC, `City_State_nameState` ASC),
  CONSTRAINT `fk_Address_City1`
    FOREIGN KEY (`City_nameCity` , `City_State_nameState`)
    REFERENCES `management-sys`.`City` (`nameCity` , `State_nameState`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Address_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `management-sys`.`Person` (`idDocumentPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Brand` (
  `idBrand` INT NULL AUTO_INCREMENT,
  `nameBrand` VARCHAR(45) NOT NULL,
  `isActiveBrand` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idBrand`),
  UNIQUE INDEX `nameBrand_UNIQUE` (`nameBrand` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`EmployeeType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`EmployeeType` (
  `idEmployeeType` INT(11) NOT NULL AUTO_INCREMENT,
  `nameEmployeeType` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idEmployeeType`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`LegalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`LegalPerson` (
  `rgLegalPerson` VARCHAR(45) NOT NULL,
  `Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_LegalPerson_Person1_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_LegalPerson_Person1`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `management-sys`.`Person` (`idDocumentPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Employee` (
  `loginEmployee` VARCHAR(30) NOT NULL,
  `passwordEmployee` VARCHAR(50) NOT NULL,
  `EmployeeType_idEmployeeType` INT(11) NOT NULL,
  `LegalPerson_Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`LegalPerson_Person_idPerson`),
  INDEX `fk_Employee_EmployeeType1_idx` (`EmployeeType_idEmployeeType` ASC),
  INDEX `fk_Employee_LegalPerson1_idx` (`LegalPerson_Person_idPerson` ASC),
  UNIQUE INDEX `loginEmployee_UNIQUE` (`loginEmployee` ASC),
  CONSTRAINT `fk_Employee_EmployeeType1`
    FOREIGN KEY (`EmployeeType_idEmployeeType`)
    REFERENCES `management-sys`.`EmployeeType` (`idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_LegalPerson1`
    FOREIGN KEY (`LegalPerson_Person_idPerson`)
    REFERENCES `management-sys`.`LegalPerson` (`Person_idPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`JuridicalPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`JuridicalPerson` (
  `Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Person_idPerson`),
  INDEX `fk_JuridicalPerson_Person_idx` (`Person_idPerson` ASC),
  CONSTRAINT `fk_JuridicalPerson_Person`
    FOREIGN KEY (`Person_idPerson`)
    REFERENCES `management-sys`.`Person` (`idDocumentPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Product` (
  `idProduct` INT NULL AUTO_INCREMENT,
  `barCodeProduct` VARCHAR(255) NOT NULL,
  `nameProduct` VARCHAR(255) NOT NULL,
  `priceProduct` FLOAT NOT NULL,
  `quantityProduct` INT(11) NOT NULL,
  `Brand_nameBrand` VARCHAR(45) NOT NULL,
  `isActiveProduct` TINYINT(1) NOT NULL,
  INDEX `fk_Product_Brand1_idx` (`Brand_nameBrand` ASC),
  UNIQUE INDEX `barCodeProduct_UNIQUE` (`barCodeProduct` ASC),
  PRIMARY KEY (`idProduct`),
  CONSTRAINT `fk_Product_Brand1`
    FOREIGN KEY (`Brand_nameBrand`)
    REFERENCES `management-sys`.`Brand` (`nameBrand`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Registry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Registry` (
  `idRegistry` INT(11) NOT NULL,
  `dateRegistry` DATE NOT NULL,
  `valueRegistry` FLOAT NOT NULL,
  `typeRegistry` INT(11) NOT NULL,
  `Person_customer` VARCHAR(50) NOT NULL,
  `Person_employee` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idRegistry`),
  INDEX `fk_Registry_Person1_idx` (`Person_customer` ASC),
  INDEX `fk_Registry_Person2_idx` (`Person_employee` ASC),
  CONSTRAINT `fk_Registry_Person1`
    FOREIGN KEY (`Person_customer`)
    REFERENCES `management-sys`.`Person` (`idDocumentPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Registry_Person2`
    FOREIGN KEY (`Person_employee`)
    REFERENCES `management-sys`.`Person` (`idDocumentPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`ServiceStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`ServiceStatus` (
  `idServiceStatus` INT(11) NOT NULL AUTO_INCREMENT,
  `nameServiceStatus` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idServiceStatus`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`ServiceType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`ServiceType` (
  `idServiceType` INT NULL AUTO_INCREMENT,
  `nameServiceType` VARCHAR(45) NOT NULL,
  `priceServiceType` VARCHAR(45) NOT NULL,
  `isActiveServiceType` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idServiceType`),
  UNIQUE INDEX `nameServiceType_UNIQUE` (`nameServiceType` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Supplier` (
  `JuridicalPerson_Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`JuridicalPerson_Person_idPerson`),
  CONSTRAINT `fk_Supplier_JuridicalPerson1`
    FOREIGN KEY (`JuridicalPerson_Person_idPerson`)
    REFERENCES `management-sys`.`JuridicalPerson` (`Person_idPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Brand_has_Supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Brand_has_Supplier` (
  `Brand_nameBrand` VARCHAR(45) NOT NULL,
  `Supplier_JuridicalPerson_Person_idPerson` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Brand_nameBrand`, `Supplier_JuridicalPerson_Person_idPerson`),
  INDEX `fk_Brand_has_Supplier_Supplier1_idx` (`Supplier_JuridicalPerson_Person_idPerson` ASC),
  INDEX `fk_Brand_has_Supplier_Brand1_idx` (`Brand_nameBrand` ASC),
  CONSTRAINT `fk_Brand_has_Supplier_Brand1`
    FOREIGN KEY (`Brand_nameBrand`)
    REFERENCES `management-sys`.`Brand` (`nameBrand`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Brand_has_Supplier_Supplier1`
    FOREIGN KEY (`Supplier_JuridicalPerson_Person_idPerson`)
    REFERENCES `management-sys`.`Supplier` (`JuridicalPerson_Person_idPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`Product_has_Registry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`Product_has_Registry` (
  `Product_barCodeProduct` VARCHAR(255) NOT NULL,
  `Registry_idRegistry` INT(11) NOT NULL,
  `Product_has_RegistryQuantity` INT NOT NULL,
  `Product_has_RegistryIndividualPrice` FLOAT NOT NULL,
  PRIMARY KEY (`Product_barCodeProduct`, `Registry_idRegistry`),
  INDEX `fk_Product_has_Registry_Registry1_idx` (`Registry_idRegistry` ASC),
  INDEX `fk_Product_has_Registry_Product1_idx` (`Product_barCodeProduct` ASC),
  CONSTRAINT `fk_Product_has_Registry_Product1`
    FOREIGN KEY (`Product_barCodeProduct`)
    REFERENCES `management-sys`.`Product` (`barCodeProduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Product_has_Registry_Registry1`
    FOREIGN KEY (`Registry_idRegistry`)
    REFERENCES `management-sys`.`Registry` (`idRegistry`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `management-sys`.`St_has_Registry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `management-sys`.`St_has_Registry` (
  `idSt_has_Registry` INT NULL AUTO_INCREMENT,
  `ServiceType_idServiceType` INT NOT NULL,
  `Registry_idRegistry` INT(11) NOT NULL,
  `ServiceStatus_idServiceStatus` INT(11) NOT NULL,
  `startDateSt_has_Registry` DATE NOT NULL,
  `estimatedDateSt_has_Registry` DATE NOT NULL,
  `finalDateSt_has_Registry` DATE NULL,
  `Person_idEmployee` INT NOT NULL,
  `IndividualPrice_St_has_Registry` FLOAT NOT NULL,
  `messageSt_has_Registry` VARCHAR(200) NULL,
  INDEX `fk_ServiceType_has_Registry_Registry1_idx` (`Registry_idRegistry` ASC),
  INDEX `fk_ServiceType_has_Registry_ServiceType1_idx` (`ServiceType_idServiceType` ASC),
  INDEX `fk_ServiceType_has_Registry_ServiceStatus1_idx` (`ServiceStatus_idServiceStatus` ASC),
  INDEX `fk_St_has_Registry_Person1_idx` (`Person_idEmployee` ASC),
  PRIMARY KEY (`idSt_has_Registry`),
  CONSTRAINT `fk_ServiceType_has_Registry_ServiceType1`
    FOREIGN KEY (`ServiceType_idServiceType`)
    REFERENCES `management-sys`.`ServiceType` (`idServiceType`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ServiceType_has_Registry_Registry1`
    FOREIGN KEY (`Registry_idRegistry`)
    REFERENCES `management-sys`.`Registry` (`idRegistry`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ServiceType_has_Registry_ServiceStatus1`
    FOREIGN KEY (`ServiceStatus_idServiceStatus`)
    REFERENCES `management-sys`.`ServiceStatus` (`idServiceStatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_St_has_Registry_Person1`
    FOREIGN KEY (`Person_idEmployee`)
    REFERENCES `management-sys`.`Person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
