package com.yourname.library.util;

import com.traneptora.jxlatte.JXLDecoder;
import com.traneptora.jxlatte.JXLImage;
import com.traneptora.jxlatte.io.PNGWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

public final class AvatarImageService {

    private static final Path IMAGE_DIRECTORY =
            Paths.get("image").toAbsolutePath().normalize();
    private static final Path AVATAR_FILE = IMAGE_DIRECTORY.resolve("TouXiang.png");

    private AvatarImageService() {
    }

    public static void ensureImageDirectory() throws IOException {
        Files.createDirectories(IMAGE_DIRECTORY);
    }

    public static BufferedImage loadAvatar() throws IOException {
        ensureImageDirectory();
        if (!Files.isRegularFile(AVATAR_FILE)) {
            return null;
        }

        BufferedImage image = ImageIO.read(AVATAR_FILE.toFile());
        if (image == null) {
            throw new IOException("保存的头像文件无法识别");
        }
        return image;
    }

    public static BufferedImage importAvatar(File sourceFile) throws IOException {
        if (sourceFile == null || !sourceFile.isFile()) {
            throw new IOException("选择的图片文件不存在");
        }

        ensureImageDirectory();
        Path temporaryPng = Files.createTempFile(IMAGE_DIRECTORY, "avatar-", ".png");

        try {
            String extension = getExtension(sourceFile.getName());
            if ("jxl".equals(extension)) {
                decodeJxl(sourceFile.toPath(), temporaryPng);
            } else if ("png".equals(extension)
                    || "jpg".equals(extension)
                    || "jpeg".equals(extension)) {
                convertStandardImage(sourceFile, temporaryPng);
            } else {
                throw new IOException("仅支持 JXL、PNG、JPG 和 JPEG 图片");
            }

            BufferedImage image = ImageIO.read(temporaryPng.toFile());
            if (image == null) {
                throw new IOException("图片内容无效或已经损坏");
            }

            Files.move(
                    temporaryPng,
                    AVATAR_FILE,
                    StandardCopyOption.REPLACE_EXISTING
            );
            return image;
        } finally {
            Files.deleteIfExists(temporaryPng);
        }
    }

    private static void convertStandardImage(File sourceFile, Path outputFile)
            throws IOException {
        BufferedImage image = ImageIO.read(sourceFile);
        if (image == null) {
            throw new IOException("图片内容无效或已经损坏");
        }
        if (!ImageIO.write(image, "png", outputFile.toFile())) {
            throw new IOException("无法将图片保存为 PNG 格式");
        }
    }

    private static void decodeJxl(Path inputFile, Path outputFile) throws IOException {
        try (InputStream input = Files.newInputStream(inputFile);
             OutputStream output = Files.newOutputStream(outputFile);
             JXLDecoder decoder = new JXLDecoder(input)) {
            JXLImage image = decoder.decode();
            if (image == null) {
                throw new IOException("JPEG XL 图片中没有可显示的静态画面");
            }
            new PNGWriter(image).write(output);
        } catch (IOException | RuntimeException e) {
            throw new IOException("JPEG XL 图片解析失败：" + e.getMessage(), e);
        }
    }

    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
    }
}
