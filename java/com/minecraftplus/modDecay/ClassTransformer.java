package com.minecraftplus.modDecay;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ClassTransformer implements IClassTransformer
{
	@Override
	public byte[] transform(String par1, String par2, byte[] par3)
	{
		if (par1.equals("aoc"))
		{
			System.out.println("--OBFUSCATED TRANSFORMER--");
			System.out.println("ABOUT TO PATCH: " + par1);
			return patchClassASM(par1, par3, true);
		}

		if (par1.equals("net.minecraft.block.BlockTorch"))
		{
			System.out.println("--DEOBFUSCATED TRANSFORMER--");
			System.out.println("ABOUT TO PATCH: " + par1);
			return patchClassASM(par1, par3, false);
		}

		return par3;
	}

	public byte[] patchClassASM(String par1, byte[] par2, boolean par3)
	{
		String targetMethodName = par3 ? "a" : "updateTick";

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(par2);
		classReader.accept(classNode, 0);

		Iterator<MethodNode> methods = classNode.methods.iterator();
		while(methods.hasNext())
		{
			MethodNode methodNode = methods.next();
			int nodeIndex = -1;

			if ((methodNode.name.equals(targetMethodName) && methodNode.desc.equals("(Lnet/minecraft/world/World;IIILjava/util/Random;)V")))
			{
				System.out.println("--METHOD FOUND--");
				System.out.println("ABOUT TO PATCH: " + methodNode.name);

				AbstractInsnNode targetNode = null;

				@SuppressWarnings("unchecked")
				Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

				int i = -1;

				while (iter.hasNext())
				{
					i++;
					AbstractInsnNode node = iter.next();

					System.out.println(i + " NODE: " + node.getClass().getName() + " | OPC: " + node.getOpcode() + " | TYPE: " + node.getType());

					if (node.getOpcode() == -1 && node.getType() == 14)
					{
						targetNode = node;
						nodeIndex = i;
						System.out.println("--NODE FOUND--");
						System.out.println("ABOUT TO INJECT TO: " + nodeIndex);
						break;
					}
				}

				InsnList insn = new InsnList();
				LabelNode L2 = new LabelNode();
				LabelNode L1 = new LabelNode();

				System.out.println("...PATCHING...");
				/*insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/world/World", "isRemote", "Z"));
				insn.add(new JumpInsnNode(Opcodes.IFNE, L2));
				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/world/World", "rand", "Ljava/util/Random;"));
				insn.add(new IntInsnNode(Opcodes.BIPUSH, 24));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/Random", "nextInt", "(I)I"));
				insn.add(new JumpInsnNode(Opcodes.IFEQ, L1));
				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/World", "isRaining", "()Z"));
				insn.add(new JumpInsnNode(Opcodes.IFEQ, L2));
				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 2));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 3));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 4));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/World", "canBlockSeeTheSky", "(III)Z"));
				insn.add(new JumpInsnNode(Opcodes.IFEQ, L2));

				insn.add(L1);
				insn.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 2));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 3));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 4));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/World", "getBlockMetadata", "(III)I"));
				insn.add(new VarInsnNode(Opcodes.ISTORE, 6));

				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 2));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 3));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 4));
				insn.add(new FieldInsnNode(Opcodes.GETSTATIC, "com/minecraftplus/modDecay/MCP_Decay", "torchUnlit", "Lnet/minecraft/block/Block"));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/World", "setBlock", "(IIILnet/minecraft/block/Block;)Z"));
				insn.add(new InsnNode(Opcodes.POP));

				insn.add(new VarInsnNode(Opcodes.ALOAD, 1));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 2));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 3));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 4));
				insn.add(new VarInsnNode(Opcodes.ILOAD, 6));
				insn.add(new InsnNode(Opcodes.ICONST_2));
				insn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/World", "setBlockMetadataWithNotify", "(IIIII)Z"));
				insn.add(new InsnNode(Opcodes.POP));

				insn.add(L2);
				insn.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));*/

				System.out.println("...INJECTING...");
				methodNode.instructions.insert(targetNode, insn);

				System.out.println("--COMPLETED PATCHING--");
				break;
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
