create table leave_requests
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT       not null,
    start_date  DATE         not null,
    end_date    DATE         not null,
    type        varchar(255) not null,
    status      varchar(255) not null,
    decided_by  BIGINT       null,
    decided_at  DATE         null,
    created_at  DATETIME     null,

    CONSTRAINT leave_request_employees_id_fk
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);
