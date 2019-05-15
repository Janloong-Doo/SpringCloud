-- 获取本周其实数据
select DATEADD(week, DATEDIFF(week, 0, getdate()), 0)
-- 获取上周其实数据
select dateadd(week, -1, DATEADD(week, DATEDIFF(week, 0, getdate()), 0))
-- 获取上上周其实数据
select dateadd(week, -2, DATEADD(week, DATEDIFF(week, 0, getdate()), 0))
-- 修改为本月起始时间
select DATEADD(month, DATEDIFF(month, 0, getdate()), 0)
-- 获取本月末时间
select dateadd(day, -1, DATEADD(month, DATEDIFF(month, 0, getdate()) + 1, 0))