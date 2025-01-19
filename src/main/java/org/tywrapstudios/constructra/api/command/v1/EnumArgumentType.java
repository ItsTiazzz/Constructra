package org.tywrapstudios.constructra.api.command.v1;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumArgumentType<T extends Enum<T>> implements ArgumentType<T> {
    private static final Dynamic2CommandExceptionType INVALID_ENUM = new Dynamic2CommandExceptionType(
            (found, constants) -> Text.translatable("text.constructra.command.error.invalid_enum", constants, found));
    private final Class<T> enumClass;

    public static <T extends Enum<T>> EnumArgumentType<T> enumArgument(Class<T> enumClass) {
        return new EnumArgumentType<>(enumClass);
    }

    protected EnumArgumentType(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readUnquotedString();
        try {
            return Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException e) {
            throw INVALID_ENUM.createWithContext(reader, name, Arrays.toString(Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray()));
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(Stream.of(enumClass.getEnumConstants()).map(Enum::name), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Stream.of(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    public static class Info<T extends Enum<T>> implements ArgumentSerializer<EnumArgumentType<T>, Info<T>.Template> {
        @Override
        public void writePacket(Template properties, PacketByteBuf buf) {
            buf.writeString(properties.enumClass.getName());
        }

        @Override
        public Template fromPacket(PacketByteBuf buf) {
            try {
                String name = buf.readString();
                return new Template((Class<T>) Class.forName(name));
            }
            catch (ClassNotFoundException e) {
                return null;
            }
        }

        @Override
        public void writeJson(Template properties, JsonObject json) {
            json.addProperty("enum", properties.enumClass.getName());
        }

        @Override
        public Template getArgumentTypeProperties(EnumArgumentType<T> argumentType) {
            return new Template(argumentType.enumClass);
        }

        public class Template implements ArgumentSerializer.ArgumentTypeProperties<EnumArgumentType<T>> {
            final Class<T> enumClass;

            Template(Class<T> enumClass) {
                this.enumClass = enumClass;
            }

            @Override
            public EnumArgumentType<T> createType(CommandRegistryAccess commandRegistryAccess) {
                return new EnumArgumentType<>(this.enumClass);
            }

            @Override
            public ArgumentSerializer<EnumArgumentType<T>, ?> getSerializer() {
                return Info.this;
            }
        }
    }
}
