package jp.co.kin.common.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import jp.co.kin.common.test.BaseCommonTest;

/**
 * {@link CollectionUtil} のjUnit
 *
 */
public class CollectionUtilTest extends BaseCommonTest {

    @Test
    public void existsCountTest() {
        {
            List<String> list = Arrays.asList("");
            int count = 1;
            assertEquals(CollectionUtil.existsCount(list, count), true);
        }
        {
            List<String> list = Arrays.asList("1", "2");
            int count = 1;
            assertEquals(CollectionUtil.existsCount(list, count), false);
        }
        {
            List<String> list = null;
            int count = 1;
            assertEquals(CollectionUtil.existsCount(list, count), false);
        }
    }

    @Test
    public void isEmptyTest() {
        {
            List<String> list = null;
            assertEquals(CollectionUtil.isEmpty(list), true);
        }
        {
            List<String> list = new ArrayList<>();
            assertEquals(CollectionUtil.isEmpty(list), true);
        }
        {
            List<String> list = Arrays.asList("1");
            assertEquals(CollectionUtil.isEmpty(list), false);
        }
        {
            List<String> list = Arrays.asList("1", "2");
            assertEquals(CollectionUtil.isEmpty(list), false);
        }
    }

    @Test
    public void isMultipleTest() {
        {
            List<String> list = null;
            assertEquals(CollectionUtil.isMultiple(list), false);
        }
        {
            List<String> list = new ArrayList<>();
            assertEquals(CollectionUtil.isMultiple(list), false);
        }
        {
            List<String> list = Arrays.asList("1");
            assertEquals(CollectionUtil.isMultiple(list), false);
        }
        {
            List<String> list = Arrays.asList("1", "2");
            assertEquals(CollectionUtil.isMultiple(list), true);
        }
    }

    @Test
    public void existsTest() {
        {
            List<String> list = null;
            assertEquals(CollectionUtil.exists(list), false);
        }
        {
            List<String> list = new ArrayList<>();
            assertEquals(CollectionUtil.exists(list), false);
        }
        {
            List<String> list = Arrays.asList("1");
            assertEquals(CollectionUtil.exists(list), true);
        }
    }
}
