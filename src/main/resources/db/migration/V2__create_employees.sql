CREATE TABLE employees (
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NULL,
    department_id BIGINT       NOT NULL,
    job_title     VARCHAR(255) NOT NULL,
    status        VARCHAR(255) NOT NULL,
    role          VARCHAR(255) NOT NULL,
    start_date    DATE         NOT NULL,
    created_at    DATETIME     NOT NULL,

    CONSTRAINT employee_departments_id_fk
    FOREIGN KEY (department_id) REFERENCES departments (id)
);