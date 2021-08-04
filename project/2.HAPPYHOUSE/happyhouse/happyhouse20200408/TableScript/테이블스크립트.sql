CREATE TABLE HouseDeal
(
    `no`          INT            NOT NULL    AUTO_INCREMENT COMMENT '거래번호', 
    `dong`        VARCHAR(30)    NOT NULL    COMMENT '법정동', 
    `AptName`     VARCHAR(30)    NOT NULL    COMMENT '집이름', 
    `code`        VARCHAR(30)    NOT NULL    COMMENT '법정동코드', 
    `dealAmount`  VARCHAR(30)    NOT NULL    COMMENT '거래금액', 
    `buildYear`   VARCHAR(30)    NOT NULL    COMMENT '건축년도', 
    `dealYear`    VARCHAR(30)    NOT NULL    COMMENT '거래년도', 
    `dealMonth`   VARCHAR(30)    NOT NULL    COMMENT '거래월', 
    `dealDay`     VARCHAR(30)    NOT NULL    COMMENT '거래일', 
    `area`        VARCHAR(30)    NOT NULL    COMMENT '면적', 
    `floor`       VARCHAR(30)    NOT NULL    COMMENT '층', 
    `jibun`       VARCHAR(30)    NOT NULL    COMMENT '지번', 
    `type`        VARCHAR(30)    NOT NULL    COMMENT '주택거래타입', 
    `rendMoney`   VARCHAR(30)    NULL        COMMENT '월세', 
    PRIMARY KEY (no)
);

ALTER TABLE HouseDeal COMMENT '집거래내역';
CREATE TABLE Store
(
    `no`            INT            NOT NULL    AUTO_INCREMENT COMMENT '상가번호', 
    `shopname`      VARCHAR(45)    NULL        COMMENT '상가명', 
    `localname`     VARCHAR(45)    NULL        COMMENT '지역명', 
    `code1`         VARCHAR(30)    NULL        COMMENT '대분류코드', 
    `codename1`     VARCHAR(30)    NULL        COMMENT '대분류명', 
    `code2`         VARCHAR(30)    NULL        COMMENT '중분류코드', 
    `codename2`     VARCHAR(30)    NULL        COMMENT '중분류명', 
    `code3`         VARCHAR(30)    NULL        COMMENT '소분류코드', 
    `codename3`     VARCHAR(30)    NULL        COMMENT '소분류명', 
    `code4`         VARCHAR(30)    NULL        COMMENT '표준산업분류코드', 
    `codename4`     VARCHAR(30)    NULL        COMMENT '표준산업분류명', 
    `citycode`      VARCHAR(30)    NULL        COMMENT '시도코드', 
    `city`          VARCHAR(30)    NULL        COMMENT '시도명', 
    `gucode`        VARCHAR(30)    NULL        COMMENT '시군구코드', 
    `gu`            VARCHAR(30)    NULL        COMMENT '시군구명', 
    `dongcode`      VARCHAR(30)    NULL        COMMENT '동코드', 
    `dong`          VARCHAR(30)    NULL        COMMENT '동명', 
    `jibunaddress`  VARCHAR(30)    NULL        COMMENT '지번주소', 
    `address`       VARCHAR(30)    NULL        COMMENT '도로명주소', 
    `oldpostcode`   VARCHAR(30)    NULL        COMMENT '구우편번호', 
    `postcode`      VARCHAR(30)    NULL        COMMENT '신우편번호', 
    `lng`           VARCHAR(30)    NULL        COMMENT '위도', 
    `lat`           VARCHAR(30)    NULL        COMMENT '경도', 
    PRIMARY KEY (no)
);

ALTER TABLE Store COMMENT '상가정보';
CREATE TABLE Member
(
    `id`               VARCHAR(20)     NOT NULL    COMMENT '아이디', 
    `password`         VARCHAR(15)     NOT NULL    COMMENT '비밀번호', 
    `name`             VARCHAR(30)     NOT NULL    COMMENT '이름', 
    `address_default`  VARCHAR(100)    NOT NULL    COMMENT '기본주소', 
    `phoneNum`         VARCHAR(15)     NOT NULL    COMMENT '휴대폰전화번호', 
    `address_detail`   VARCHAR(100)    NOT NULL    COMMENT '상세주소', 
    `postNum`          VARCHAR(6)      NOT NULL    COMMENT '우편번호', 
    `homeNum`          VARCHAR(15)     NULL        COMMENT '집전화번호', 
    PRIMARY KEY (id)
);

ALTER TABLE Member COMMENT '회원';
CREATE TABLE House
(
    `no`         INT            NOT NULL    AUTO_INCREMENT COMMENT '집번호', 
    `dong`       VARCHAR(30)    NOT NULL    COMMENT '법정동', 
    `AptName`    VARCHAR(50)    NOT NULL    COMMENT '집이름', 
    `code`       VARCHAR(30)    NOT NULL    COMMENT '법정동코드', 
    `buildYear`  VARCHAR(30)    NOT NULL    COMMENT '건축년도', 
    `jibun`      VARCHAR(30)    NOT NULL    COMMENT '지번', 
    `lat`        VARCHAR(30)    NOT NULL    COMMENT '위도', 
    `lng`        VARCHAR(30)    NOT NULL    COMMENT '경도', 
    `img`        VARCHAR(50)    NOT NULL    COMMENT '주택이미지', 
    PRIMARY KEY (no)
);

ALTER TABLE House COMMENT '집정보';

CREATE TABLE AreaOfInterest
(
    `id`    VARCHAR(20)    NOT NULL    COMMENT '아이디', 
    `code`  VARCHAR(10)    NOT NULL    COMMENT '법정동코드', 
    PRIMARY KEY (id, code)
);

ALTER TABLE AreaOfInterest COMMENT '관심지역';

ALTER TABLE AreaOfInterest
    ADD CONSTRAINT FK_AreaOfInterest_code_Region_code FOREIGN KEY (code)
        REFERENCES Region (code) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE AreaOfInterest
    ADD CONSTRAINT FK_AreaOfInterest_id_Member_id FOREIGN KEY (id)
        REFERENCES Member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
-- CREATE TABLE AreaOfInterest
-- (
--     `id`    VARCHAR(20)    NOT NULL    COMMENT '아이디', 
--     `code`  VARCHAR(10)    NOT NULL    COMMENT '법정동코드', 
--     `no`    INT            NOT NULL    AUTO_INCREMENT COMMENT 'no', 
--     PRIMARY KEY (no)
-- );

-- ALTER TABLE AreaOfInterest COMMENT '관심지역';

-- ALTER TABLE AreaOfInterest
--     ADD CONSTRAINT FK_AreaOfInterest_code_Region_code FOREIGN KEY (code)
--         REFERENCES Region (code) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- ALTER TABLE AreaOfInterest
--     ADD CONSTRAINT FK_AreaOfInterest_id_Member_id FOREIGN KEY (id)
--         REFERENCES Member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
CREATE TABLE Region
(
    `code`           VARCHAR(10)    NOT NULL    COMMENT '법정동코드', 
    `doMegalopolis`  VARCHAR(45)    NULL        COMMENT '도광역시', 
    `cityGuGun`      VARCHAR(45)    NULL        COMMENT '시구군', 
    `dong`           VARCHAR(45)    NULL        COMMENT '동', 
    PRIMARY KEY (code)
);

ALTER TABLE Region COMMENT '지역';