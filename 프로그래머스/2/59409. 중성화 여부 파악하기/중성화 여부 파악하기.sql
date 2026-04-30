-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, CASE
                        WHEN (SEX_UPON_INTAKE LIKE 'N%')
                        OR (SEX_UPON_INTAKE LIKE 'S%') THEN 'O'
                        ELSE 'X'
                        END AS '중성화'
FROM ANIMAL_INS