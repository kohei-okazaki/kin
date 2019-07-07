UPDATE USER_ONTIME_DATA
SET
  WORK_START_HOUR = /* entity.workStartHour */'1',
  WORK_START_MINUTE = /* entity.workStartMinute */'1',
  WORK_END_HOUR = /* entity.workEndHour */'1',
  WORK_END_MINUTE = /* entity.workEndMinute */'1',
  UPDATE_DATE = /* entity.updateDate */'2019/01/01'
WHERE
  LOGIN_ID = /* entity.loginId */'1';