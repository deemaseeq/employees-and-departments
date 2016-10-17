--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: emps-and-depts; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA "emps-and-depts";


SET search_path = "emps-and-depts", pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: department; Type: TABLE; Schema: emps-and-depts; Owner: -
--

CREATE TABLE department (
    department_id integer NOT NULL,
    department_name character varying(255) NOT NULL
);


--
-- Name: employee; Type: TABLE; Schema: emps-and-depts; Owner: -
--

CREATE TABLE employee (
    employee_id integer NOT NULL,
    employee_name character varying(255) NOT NULL,
    employee_email character varying(255) NOT NULL,
    employee_hire_date date NOT NULL,
    employee_salary real,
    employee_department_id integer NOT NULL
);


--
-- Name: employee_employee_id_seq; Type: SEQUENCE; Schema: emps-and-depts; Owner: -
--

CREATE SEQUENCE employee_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: employee_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: emps-and-depts; Owner: -
--

ALTER SEQUENCE employee_employee_id_seq OWNED BY employee.employee_id;


--
-- Name: employee_id; Type: DEFAULT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY employee ALTER COLUMN employee_id SET DEFAULT nextval('employee_employee_id_seq'::regclass);


--
-- Data for Name: department; Type: TABLE DATA; Schema: emps-and-depts; Owner: -
--

INSERT INTO department VALUES (1, 'Sales and Marketing');
INSERT INTO department VALUES (2, 'HR');
INSERT INTO department VALUES (3, 'Finance');
INSERT INTO department VALUES (4, 'Development');


--
-- Data for Name: employee; Type: TABLE DATA; Schema: emps-and-depts; Owner: -
--

INSERT INTO employee VALUES (1, 'SMITH JOHN', '@1', '1984-12-17', 800, 1);
INSERT INTO employee VALUES (2, 'ALLEN KEVIN', '@2', '1985-02-20', 1600, 1);
INSERT INTO employee VALUES (3, 'DOYLE JEAN', '@3', '1985-04-04', 2850, 1);
INSERT INTO employee VALUES (4, 'DENNIS LYNN', '@4', '1985-05-15', 2750, 1);
INSERT INTO employee VALUES (5, 'BAKER LESLIE', '@5', '1985-06-10', 2200, 1);
INSERT INTO employee VALUES (6, 'WARD CYNTHIA', '@6', '1985-02-22', 1250, 1);
INSERT INTO employee VALUES (7, 'PETERS DANIEL', '@7', '1985-03-31', 1250, 1);
INSERT INTO employee VALUES (8, 'SHAW KAREN', '@8', '1985-04-02', 1250, 1);
INSERT INTO employee VALUES (9, 'DUNCAN SARAH', '@9', '1985-05-31', 1250, 2);
INSERT INTO employee VALUES (10, 'LANGE GREGORY', '@10', '1985-06-01', 1250, 2);
INSERT INTO employee VALUES (11, 'JONES TERRY', '@11', '1985-04-02', 2975, 2);
INSERT INTO employee VALUES (12, 'ALBERTS CHRIS', '@12', '1985-04-06', 3000, 2);
INSERT INTO employee VALUES (13, 'PORTER RAYMOND', '@13', '1985-04-15', 1250, 2);
INSERT INTO employee VALUES (14, 'LEWIS RICHARD', '@14', '1985-04-16', 1800, 2);
INSERT INTO employee VALUES (15, 'MARTIN KENNETH', '@15', '1985-09-28', 1250, 2);
INSERT INTO employee VALUES (16, 'SOMMERS DENISE', '@16', '1985-04-19', 1850, 2);
INSERT INTO employee VALUES (17, 'BLAKE MARION', '@17', '1985-05-01', 2850, 3);
INSERT INTO employee VALUES (18, 'CLARK CAROL', '@18', '1985-06-09', 2450, 3);
INSERT INTO employee VALUES (19, 'SCOTT DONALD', '@19', '1986-12-09', 3000, 3);
INSERT INTO employee VALUES (20, 'WEST LIVIA', '@20', '1985-04-04', 1500, 3);
INSERT INTO employee VALUES (21, 'FISHER MATTHEW', '@21', '1986-12-12', 3000, 3);
INSERT INTO employee VALUES (22, 'ROSS PAUL', '@22', '1985-06-01', 1300, 3);
INSERT INTO employee VALUES (23, 'KING FRANCIS', '@23', '1985-11-17', 5000, 3);
INSERT INTO employee VALUES (24, 'TURNER MARY', '@24', '1985-09-08', 1500, 3);
INSERT INTO employee VALUES (25, 'ADAMS DIANE', '@25', '1987-01-12', 1100, 4);
INSERT INTO employee VALUES (26, 'JAMES FRED', '@26', '1985-12-03', 950, 4);
INSERT INTO employee VALUES (27, 'FORD JENNIFER', '@27', '1985-12-03', 3000, 4);
INSERT INTO employee VALUES (28, 'ROBERTS GRACE', '@28', '1987-01-04', 2875, 4);
INSERT INTO employee VALUES (29, 'DOUGLAS MICHAEL', '@29', '1987-01-04', 800, 4);
INSERT INTO employee VALUES (30, 'MILLER BARBARA', '@30', '1986-01-23', 1300, 4);
INSERT INTO employee VALUES (31, 'JENSEN ALICE', '@31', '1987-01-15', 750, 4);
INSERT INTO employee VALUES (32, 'MURRAY JAMES', '@32', '1987-01-16', 750, 4);


--
-- Name: employee_employee_id_seq; Type: SEQUENCE SET; Schema: emps-and-depts; Owner: -
--

SELECT pg_catalog.setval('employee_employee_id_seq', 40, true);


--
-- Name: department_pkey; Type: CONSTRAINT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- Name: employee_pkey; Type: CONSTRAINT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (employee_id);


--
-- Name: unique_department_name; Type: CONSTRAINT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY department
    ADD CONSTRAINT unique_department_name UNIQUE (department_name);


--
-- Name: unique_employee_email; Type: CONSTRAINT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT unique_employee_email UNIQUE (employee_email);


--
-- Name: fk_employee; Type: FK CONSTRAINT; Schema: emps-and-depts; Owner: -
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT fk_employee FOREIGN KEY (employee_department_id) REFERENCES department(department_id) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

