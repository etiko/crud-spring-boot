CREATE TABLE employees (
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    department_id BIGINT       NULL,

    CONSTRAINT employee_departments_id_fk
    FOREIGN KEY (department_id) REFERENCES departments (id)
);