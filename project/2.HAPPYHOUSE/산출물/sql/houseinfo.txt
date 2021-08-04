CREATE TABLE `houseinfo` (
  `no` int DEFAULT NULL,
  `dong` text,
  `AptName` text,
  `code` int DEFAULT NULL,
  `buildYear` int DEFAULT NULL,
  `jibun` text,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `img` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci