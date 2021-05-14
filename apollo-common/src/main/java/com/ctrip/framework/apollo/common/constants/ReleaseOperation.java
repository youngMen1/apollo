package com.ctrip.framework.apollo.common.constants;

/**
 * 发布操作类型
 *
 * @author Jason Song(song_s@ctrip.com)
 */
public interface ReleaseOperation {
    /**
     * 主干发布
     */
    int NORMAL_RELEASE = 0;
    /**
     * 回滚
     */
    int ROLLBACK = 1;
    /**
     * 灰度发布
     */
    int GRAY_RELEASE = 2;
    /**
     * 变更灰度配置
     */
    int APPLY_GRAY_RULES = 3;
    int GRAY_RELEASE_MERGE_TO_MASTER = 4;
    /**
     * 主干发布时，合并配置到分支
     */
    int MASTER_NORMAL_RELEASE_MERGE_TO_GRAY = 5;
    int MATER_ROLLBACK_MERGE_TO_GRAY = 6;
    /**
     * 放弃灰度发布
     */
    int ABANDON_GRAY_RELEASE = 7;
    /**
     * 灰度全量发布后，合并删除分支
     */
    int GRAY_RELEASE_DELETED_AFTER_MERGE = 8;

}
