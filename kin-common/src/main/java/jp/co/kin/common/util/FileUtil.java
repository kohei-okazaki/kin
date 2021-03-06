package jp.co.kin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jp.co.kin.common.log.Logger;
import jp.co.kin.common.log.LoggerFactory;
import jp.co.kin.common.type.BaseEnum;

/**
 * File操作のUtil
 *
 * @since 1.0.0
 */
public class FileUtil {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * 指定したファイルパス配下の全ファイルのリストを返す
     *
     * @param path
     *     ファイルパス
     * @return ファイルパス配下に存在する全ファイル
     */
    public static List<File> getFileList(String path) {
        return getFileList(getFile(path));
    }

    /**
     * 指定したファイル配下の全ファイルのリストを返す
     *
     * @param file
     *     ファイル
     * @return ファイル配下に存在する全ファイル
     */
    public static List<File> getFileList(File file) {
        List<File> fileList = new ArrayList<>();
        if (file == null) {
            return fileList;
        }
        for (File childFile : file.listFiles()) {
            if (childFile.isFile()) {
                fileList.add(childFile);
            } else {
                fileList.addAll(getFileList(childFile));
            }
        }
        return fileList;
    }

    /**
     * 指定した<code>srcFileList</code>を<code>zipFile</code>に圧縮する
     *
     * @param srcFileList
     *     zipファイルに含めたいファイルのリスト
     * @param destFilePath
     *     出力先ファイルパス
     * @return zipファイル
     */
    public static File complessZip(List<File> srcFileList, String destFilePath) {

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destFilePath),
                Charset.forName("UTF-8"))) {
            byte[] buffer = new byte[1024];
            for (File file : srcFileList) {
                try (InputStream in = new FileInputStream(file)) {
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("ファイルが見つかりません destFile:" + destFilePath, e);
        } catch (IOException e) {
            LOG.error("Zipへの圧縮に失敗しました", e);
        }
        return new File(destFilePath);
    }

    public static List<File> unZip(File srcFile) {
        // TODO
        List<File> destFileList = new ArrayList<>();

        return destFileList;
    }

    /**
     * ファイルのコピーを行う
     *
     * @param srcFile
     *     コピー元ファイル
     * @param destFile
     *     コピー先ファイル
     */
    public static void copyFile(File srcFile, File destFile) {
        try (FileChannel srcChannel = new FileInputStream(srcFile).getChannel();
                FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
        } catch (FileNotFoundException e) {
            LOG.error("ファイルが見つかりません srcFile:" + srcFile.getPath() + " destFile:"
                    + destFile.getPath(), e);
        } catch (IOException e) {
            LOG.error("ファイルの書き込みや読み込みに失敗しました", e);
        }
    }

    /**
     * ファイルのコピーを行う
     *
     * @param srcPath
     *     コピー元ファイル
     * @param destPath
     *     コピー先ファイル
     */
    public static void copyFile(String srcPath, String destPath) {
        try {
            Path src = Paths.get(srcPath);
            Path dest = Paths.get(destPath);
            Files.deleteIfExists(dest);
            Files.copy(src, dest);
        } catch (IOException e) {
            LOG.error("ファイルのコピーに失敗しました srcFilePath:" + srcPath + " destFilePath:"
                    + destPath, e);
        }
    }

    /**
     * 指定したパス配下のファイルをすべて削除する
     *
     * @param path
     *     対象パス
     */
    public static void deleteFiles(String path) {

        // 削除対象のファイルを取得
        List<File> fileList = getFileList(path);

        for (File file : fileList) {
            if (file.isFile()) {
                file.delete();
            }
        }

    }

    /**
     * 指定された<code>srcPath</code>を指定された<code>sep</code>のパスに変換する
     *
     * @param srcPath
     *     パス
     * @param sep
     *     ファイルセパレータ
     * @return ファイル
     */
    public static File convertPathFile(String srcPath, FileSeparator sep) {
        switch (sep) {
        case WINDOWS:
            if (srcPath.contains(FileSeparator.WINDOWS.getValue())) {
                return getFile(srcPath);
            }
            return getFile(
                    srcPath.replaceAll(FileSeparator.LINUX.getValue(),
                            FileSeparator.WINDOWS.getValue()));

        default:
            if (srcPath.contains(FileSeparator.LINUX.getValue())) {
                return getFile(srcPath);
            }
            return getFile(
                    srcPath.replaceAll(FileSeparator.WINDOWS.getValue(),
                            FileSeparator.LINUX.getValue()));
        }
    }

    /**
     * 指定した<code>path</code>のファイルオブジェクトを返す
     *
     * @param path
     *     パス
     * @return ファイル
     */
    public static File getFile(String path) {
        return new File(path);
    }

    /**
     * 指定した<code>path</code>がファイルかどうかを返す<br>
     *
     * @param path
     *     パス
     * @return ファイルの場合true, それ以外の場合false
     */
    public static boolean isFile(String path) {
        return getFile(path).isFile();
    }

    /**
     * 指定した<code>path</code>がディレクトリかどうかを返す<br>
     *
     * @param path
     *     パス
     * @return ディレクトリの場合true, それ以外の場合false
     */
    public static boolean isDir(String path) {
        return getFile(path).isDirectory();
    }

    /**
     * 指定した<code>path</code>までのファイルオブジェクトが存在するかどうか判定する
     *
     * @param path
     *     パス
     * @return 存在するtrue, それ以外の場合false
     */
    public static boolean isExists(String path) {
        return getFile(path).exists();
    }

    /**
     * 指定した<code>path</code>までのディレクトリを作成する
     *
     * @param path
     *     パス
     * @return 作成された場合true, それ以外の場合false
     */
    public static boolean mkdir(String path) {
        return getFile(path).mkdir();
    }

    /**
     * ファイル拡張子の列挙
     */
    public static enum FileExtension implements BaseEnum {

        /** csv */
        CSV(".csv"),
        /** zip */
        ZIP(".zip"),
        /** java */
        JAVA(".java"),
        /** sql */
        SQL(".sql");

        /** 値 */
        private String value;

        private FileExtension(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public static FileExtension of(String value) {
            return BaseEnum.of(FileExtension.class, value);
        }
    }

    /**
     * ファイルセパレータの列挙
     */
    public static enum FileSeparator implements BaseEnum {

        /** Windows */
        WINDOWS("\\"),
        /** linux */
        LINUX("/"),
        /** system */
        SYSTEM(FileSystems.getDefault().getSeparator());

        /** セパレータ */
        private String value;

        private FileSeparator(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        public static FileSeparator of(String value) {
            return BaseEnum.of(FileSeparator.class, value);
        }

    }
}
