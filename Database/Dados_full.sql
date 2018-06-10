-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 07/06/2018 às 20:25
-- Versão do servidor: 10.1.32-MariaDB
-- Versão do PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `mydb`
--
INSERT INTO `State` (`nameState`) VALUES
('Acre'),
('Alagoas'),
('Amapá'),
('Amazonas'),
('Bahia'),
('Ceará'),
('Distrito Federal'),
('Espírito Santo'),
('Goiás'),
('Maranhão'),
('Mato Grosso'),
('Mato Grosso do Sul'),
('Minas Gerais'),
('Pará'),
('Paraíba'),
('Paraná'),
('Pernambuco'),
('Piauí'),
('Rio de Janeiro'),
('Rio Grande do Norte'),
('Rio Grande do Sul'),
('Rondônia'),
('Roraima'),
('Santa Catarina'),
('São Paulo'),
('Sergipe'),
('Tocantins');

--
-- Fazendo dump de dados para tabela `City`
--

INSERT INTO `City` (`nameCity`, `State_nameState`) VALUES
('Abadia de Goiás', 'Goiás'),
('Abadia dos Dourados', 'Minas Gerais'),
('Abadiânia', 'Goiás'),
('Abaeté', 'Minas Gerais'),
('Abaetetuba', 'Pará'),
('Abaiara', 'Ceará'),
('Abaíra', 'Bahia'),
('Abaré', 'Bahia'),
('Abatiá', 'Paraná'),
('Abdon Batista', 'Santa Catarina'),
('Abel Figueiredo', 'Pará'),
('Abelardo Luz', 'Santa Catarina'),
('Abre Campo', 'Minas Gerais'),
('Abreu e Lima', 'Pernambuco'),
('Abreulândia', 'Tocantins'),
('Acaiaca', 'Minas Gerais'),
('Açailândia', 'Maranhão'),
('Acajutiba', 'Bahia'),
('Acará', 'Pará'),
('Acarape', 'Ceará'),
('Acaraú', 'Ceará'),
('Acari', 'Rio Grande do Norte'),
('Acauã', 'Piauí'),
('Aceguá', 'Rio Grande do Sul'),
('Acopiara', 'Ceará'),
('Acorizal', 'Mato Grosso'),
('Acrelândia', 'Acre');

--
-- Fazendo dump de dados para tabela `Person`
--

INSERT INTO `Person` (`idPerson`, `namePerson`, `tel1Person`, `tel2Person`) VALUES
('00.000.000', 'Cliente Jurídico 1', '8811113333', '1785521474'),
('000.000.000-00', 'Cliente 1 ', '0000000000', '0000000000'),
('000.000.000-01', 'Cliente 2 ', '4300001111', '4300002222'),
('001.002.003-00', 'Cliente 3 ', '4322223333', '4322321111'),
('01.000.020', 'Fornecedor 1', '2511118888', '2177774444'),
('555.555.222-52', 'Funcionario 2', '0011112222', '1122223333'),
('88.111.333', 'Fornecedor 2', '7754543333', '1122223333'),
('888.665.777-63', 'Funcionario 1', '4300008888', '4300008888');
--
-- Fazendo dump de dados para tabela `Address`
--

INSERT INTO `Address` (`Person_idPerson`, `streetAddress`, `numberAddress`, `districtAddress`, `City_nameCity`, `City_State_nameState`) VALUES
('00.000.000', 'Rua do Cliente Juridico 1', 1, 'Bairro do Cliente Juriico 1', 'Acaiaca', 'Minas Gerais'),
('000.000.000-00', 'Rua do Cliente 1', 1, 'Bairro do Cliente 1', 'Abatiá', 'Paraná'),
('000.000.000-01', 'Rua do Cliente 2', 2, 'Bairro do Cliente 2', 'Acrelândia', 'Acre'),
('001.002.003-00', 'Rua do Cliente 3', 3, 'Bairro do Cliente 3', 'Acopiara', 'Ceará'),
('01.000.020', 'Rua do Fornecedor 1', 1, 'Bairro do Fornecedor 1', 'Acrelândia', 'Acre'),
('555.555.222-52', 'Rua do funcionário 2', 2, 'Bairro do funcionário 2', 'Açailândia', 'Maranhão'),
('88.111.333', 'Rua do Fornecedor 2 ', 2, 'Bairro do Fornecedor 2', 'Abadiânia', 'Goiás'),
('888.665.777-63', 'Rua do funcionário 1 ', 1, 'Bairro do funcionário 1', 'Acrelândia', 'Acre');

--
-- Fazendo dump de dados para tabela `LegalPerson`
--

INSERT INTO `LegalPerson` (`rgLegalPerson`, `Person_idPerson`) VALUES
('00.000.000-4', '000.000.000-00'),
('00.000.000-1', '000.000.000-01'),
('00.000.000-1', '001.002.003-00'),
('00.111.222-5', '555.555.222-52'),
('55.111.222-3', '888.665.777-63');

--
-- Fazendo dump de dados para tabela `JuridicalPerson`
--

INSERT INTO `JuridicalPerson` (`Person_idPerson`) VALUES
('00.000.000'),
('01.000.020'),
('88.111.333');

--
-- Fazendo dump de dados para tabela `Brand`
--

INSERT INTO `Brand` (`nameBrand`) VALUES
('AMD'),
('EVGA'),
('G.Skill'),
('Intel'),
('NVIDIA'),
('Samsung');

--
-- Fazendo dump de dados para tabela `Supplier`
--

INSERT INTO `Supplier` (`JuridicalPerson_Person_idPerson`) VALUES
('01.000.020'),
('88.111.333');
--
-- Fazendo dump de dados para tabela `Brand_has_Supplier`
--

INSERT INTO `Brand_has_Supplier` (`Brand_nameBrand`, `Supplier_JuridicalPerson_Person_idPerson`) VALUES
('AMD', '01.000.020'),
('EVGA', '01.000.020'),
('G.Skill', '01.000.020'),
('Intel', '01.000.020'),
('NVIDIA', '01.000.020');



--
-- Fazendo dump de dados para tabela `EmployeeType`
--

INSERT INTO `EmployeeType` (`idEmployeeType`, `nameEmployeeType`) VALUES
(1, 'MANAGER'),
(2, 'COMMON');

--
-- Fazendo dump de dados para tabela `Employee`
--

INSERT INTO `Employee` (`loginEmployee`, `passwordEmployee`, `EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson`) VALUES
('func2', '123456', 2, '555.555.222-52'),
('func1', '123456', 2, '888.665.777-63');





--
-- Fazendo dump de dados para tabela `Product`
--

INSERT INTO `Product` (`barCodeProduct`, `nameProduct`, `priceProduct`, `quantityProduct`, `Brand_nameBrand`) VALUES
('585794955', 'Processador Intel i7 4770k', 1500, 0, 'Intel'),
('6546546541654216542496', 'SSD Samsung 850 EVO 480GB', 200, 0, 'Samsung'),
('7796765468854624654', 'Memória Ram G.Skill 2400Mhz 2 x 8 GB', 700, 0, 'G.Skill'),
('878745715141', 'Placa de vídeo GTX 1070', 2000, 0, 'EVGA');

--
-- Fazendo dump de dados para tabela `ServiceStatus`
--

INSERT INTO `ServiceStatus` (`idServiceStatus`, `nameServiceStatus`) VALUES
(1, 'ON_ESTIMATE'),
(2, 'WAITING_FOR_APPROVAL'),
(3, 'WAITING_PAYMENT'),
(4, 'WAITING_FOR_WITHDRAWAL'),
(5, 'REFUSED'),
(6, 'RUNNING'),
(7, 'DONE');

--
-- Fazendo dump de dados para tabela `ServiceType`
--

INSERT INTO `ServiceType` (`idServiceType`, `nameServiceType`, `priceServiceType`) VALUES
(8, 'Limpeza de Notebook', '100.0'),
(9, 'Limpeza de Desktop ', '20.0'),
(10, 'Montagem de Desktop', '200.0');

--
-- Fazendo dump de dados para tabela `State`
--




COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
