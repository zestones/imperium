
-- populate user table
INSERT INTO users (username, firstname, lastname, password) VALUES
	('idriss', 'idriss', 'idriss', '$2b$14$CAmVnSgc7/VVsNu1EWKdHu82Aar79WVsnemti1QCMkCWtwTVcVeHG'),
	('mohammed', 'mohammed', 'mohammed', '$2b$14$e60FyOXaPNvH2sARHeVi5.59xEN2gpUltaIACRxB2C1ya4.nOxGBm'),
	('ilyes', 'ilyes', 'ilyes', '$2b$14$RfOn2AwBudVZ2YQdPb3Qcur83a5BBaoWG0WH561j4coCMWDd8Cy4y'),
	('ghilas', 'ghilas', 'ghilas', '$2b$14$edZfa4HNxpQCDi7aBWeoa.1w.d3jWmNXhSAe2OTrKM/NlnuCDjGv.'),
	('iness', 'iness', 'iness', '$2b$14$kGfybl3gKyxG9F0dwA7tHOycc3pNMnjUtWMm/7JXM7BFj03vhNijS');

-- populate project table
INSERT INTO projects (name, category, id_user) VALUES 
	('imperium', '', 1),
	('eincode', '', 2),
	('pentaroom', '', 3),
	('einvolution', '', 4),
	('einstone', '', 5);

-- populate access table
INSERT INTO access (id_user, id_project, can_edit, can_read) VALUES
	(1, 2, true, true),
	(2, 3, true, true),
	(3, 4, true, true),
	(4, 5, true, true),
	(5, 1, true, true),
	(1, 5, true, true),
	(2, 1, true, true),
	(3, 2, true, true),
	(4, 3, true, true),
	(5, 4, true, true);

-- populate board table
INSERT INTO board (title, id_project) VALUES
	('Open', 1),
	('To Do', 1),
	('In progress', 1),
	('To Check', 1),
	('Closed', 1),
	('Open', 2),
	('To Do', 2),
	('In progress', 2),
	('To Check', 2),
	('Closed', 2),
	('Open', 3),
	('To Do', 3),
	('In progress', 3),
	('To Check', 3),
	('Closed', 3),
	('Open', 4),
	('To Do', 4),
	('In progress', 4),
	('To Check', 4),
	('Closed', 4),
	('Open', 5),
	('To Do', 5),
	('In progress', 5),
	('To Check', 5),
	('Closed', 5);

-- populate task table
INSERT INTO task (title, id_board) VALUES 
	('init project', 1),
	('init project', 6),
	('init project', 11),
	('init project', 16),
	('init project', 21);

-- populate tast_users table
INSERT INTO task_users (users_id, task_id) VALUES
	(1, 5),
	(2, 3),
	(3, 4),
	(4, 5),
	(5, 1);