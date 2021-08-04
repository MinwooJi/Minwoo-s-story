use scott;
-- 1. 부서위치가 CHICAGO인 모든 사원에 대해 이름,업무,급여 출력하는 SQL
-- 을 작성하세요.
select ename,job,sal
from emp,dept
where 1=1
and dept.loc = 'chicago'
and emp.deptno = dept.deptno;

-- ansi
select ename,job,sal
from emp inner join dept
on emp.deptno = dept.deptno
where dept.LOC = 'chicago';

-- 2. 부하직원이 없는 사원의 사원번호,이름,업무,부서번호 출력하는 SQL을
-- 작성하세요.
-- ansi
select m.EMPNO,m.ename,m.job,m.DEPTNO
from emp e right outer join emp m
on e.MGR = m.EMPNO
where e.EMPNO is null;

-- 3. BLAKE와 같은 상사를 가진 사원의 이름,업무,상사번호 출력하는 SQL을
-- 작성하세요.
select ENAME,job,mgr
from emp
where mgr = (select mgr from emp where ENAME = 'BLAKE')
and ENAME not in ('BLAKE');

-- 4. 입사일이 가장 오래된 사람 5명을 검색하세요.
select *
from emp
order by HIREDATE
limit 5;
-- 5. JONES 의 부하 직원의 이름, 업무, 부서명을 검색하세요
select ename,job,DNAME
from emp inner join dept
on emp.deptno = dept.deptno
where MGR = (select empno
			from emp
			where ename = 'JONES');

