package com.jxufe.halu.Mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public class ProjectProvider {

    public String queryTable(final Map<String, Object> paramMap) {
        int page = (int) paramMap.get("page");
        int size = (int) paramMap.get("size");
        String searchParam = (String) paramMap.get("searchParam");
        String userID = (String) paramMap.get("userID");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
                "\tprojectID,\n" +
                "\tname,\n" +
                "\tcreateDate,\n" +
                "\n" +
                "IF (\n" +
                "\tsum(hasTask) = 0,\n" +
                "\t0,\n" +
                "\tsum(count * notover)\n" +
                ") AS unHandleTask,\n" +
                "\n" +
                "IF (\n" +
                "\tSUM(hasTask) = 0,\n" +
                "\t100,\n" +
                "\tsum(\n" +
                "\t\tisroot * count * notover * nodeProgress\n" +
                "\t)\n" +
                ") AS surplusProgress\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tproject.ProjectID,\n" +
                "\t\t\tproject.`Name` AS NAME,\n" +
                "\n" +
                "\t\tIF (TaskID IS NULL, 0, 1) AS hasTask,\n" +
                "\n" +
                "\tIF (task.Progress = 100, 1, 0) AS isover,\n" +
                "\n" +
                "IF (task.Progress = 100, 0, 1) AS notover,\n" +
                " NodeProgress,\n" +
                " project.CreateDate,\n" +
                "\n" +
                "IF (PTaskID IS NULL, 1, 0) AS isroot,\n" +
                " count(*) AS count\n" +
                "FROM\n" +
                "\tproject\n" +
                "LEFT JOIN mid_user_project ON project.ProjectID = mid_user_project.ProjectID\n" +
                "LEFT JOIN task ON task.ProjectID = mid_user_project.ProjectID\n" +
                "WHERE\n" +
                "\tmid_user_project.UserID = " + userID + " ");
        if (searchParam instanceof String && !searchParam.equals("")) {
            sql.append("AND `Name` LIKE '%" + searchParam + "%'  ");
        }
        sql.append("GROUP BY\n" +
                "\tproject.ProjectID,\n" +
                "\tProgress,\n" +
                "\tNodeProgress,\n" +
                "\ttask.PTaskID,\n" +
                "\tproject.CreateDate,\n" +
                "\tproject.name," +
                "task.TaskID\n" +
                "\t) AS a\n" +
                "GROUP BY\n" +
                "\tprojectID,\n" +
                "\ta. NAME,\n" +
                "\ta.createDate\n" +
                "LIMIT ");
        sql.append((--page) * size + " , " + size);
        return sql.toString();
    }

    public String countProject(final Map<String, Object> paramMap) {
        String searchParam = (String) paramMap.get("0");
        String userID = (String) paramMap.get("1");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
                "\tcount(*) as count\n" +
                "FROM\n" +
                "\t`user`\n" +
                "INNER JOIN mid_user_project ON `user`.UserID = mid_user_project.UserID\n" +
                "INNER JOIN project ON mid_user_project.ProjectID = project.ProjectID\n" +
                "WHERE\n" +
                "\t`user`.UserID = " + userID);
        if (searchParam instanceof String && !searchParam.equals("")) {
            sql.append(" AND project.`Name` LIKE '%" + searchParam + "%'");
        }
        return sql.toString();
    }
}
