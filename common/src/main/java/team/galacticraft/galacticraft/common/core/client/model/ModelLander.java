package team.galacticraft.galacticraft.common.core.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import team.galacticraft.galacticraft.common.core.entities.EntityLander;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class ModelLander extends EntityModel<EntityLander>
{
    ModelPart bodyMain1;
    ModelPart bodyMain2;
    ModelPart bodyAux1;
    ModelPart bodyAux2;
    ModelPart bodyAux3;
    ModelPart bodyAux4;
    ModelPart bodySupportMain1;
    ModelPart bodySupportMain2;
    ModelPart legStructureAux1e;
    ModelPart footBaseg;
    ModelPart legStructure1i;
    ModelPart legBase1j;
    ModelPart footJointk;
    ModelPart footBasel;
    ModelPart legBase1n;
    ModelPart legStructureAux1s;
    ModelPart legStructure1u;
    ModelPart footJointv;
    ModelPart footBasew;
    ModelPart footBasex;
    ModelPart legBase1z;
    ModelPart legStructureAux1f;
    ModelPart legStructure1g;
    ModelPart footJointh;
    ModelPart footBasei;
    ModelPart footBasej;
    ModelPart legBase1l;
    ModelPart legStructureAux1q;
    ModelPart legStructure1s;
    ModelPart footJointt;
    ModelPart footBaseu;
    ModelPart footBasev;
    ModelPart moduleMain1;
    ModelPart moduleMain2;
    ModelPart moduleMainDeco;
    ModelPart moduleCore1;
    ModelPart moduleCore2;
    ModelPart moduleCore3;
    ModelPart moduleAux1;
    ModelPart moduleAux2;
    ModelPart moduleAux3;
    ModelPart moduleAux4;
    ModelPart moduleAux5;
    ModelPart moduleAux6;
    ModelPart moduleAux7;
    ModelPart moduleAux8;
    ModelPart moduleThrusterBase1;
    ModelPart moduleThrusterBase2;
    ModelPart moduleThrusterBase3;
    ModelPart moduleThrusterBase4;
    ModelPart moduleThrusterBase5;
    ModelPart moduleThrusterConeTop1;
    ModelPart moduleThrusterConeTop2;
    ModelPart moduleThrusterConeTop3;
    ModelPart moduleThrusterConeBack1;
    ModelPart moduleThrusterConeBack2;
    ModelPart moduleThrusterConeBack3;
    ModelPart moduleThrusterConeBot1;
    ModelPart moduleThrusterConeBot2;
    ModelPart moduleThrusterConeBot3;
    ModelPart moduleThrusterConeFront1;
    ModelPart moduleThrusterConeFront2;
    ModelPart moduleThrusterConeFront3;

    public ModelLander()
    {
        this.texWidth = 512;
        this.texHeight = 512;

        this.bodyMain1 = new ModelPart(this, 125, 128);
        this.bodyMain1.addBox(-24F, 0F, -24F, 48, 12, 48);
        this.bodyMain1.setPos(0F, 0F, 0F);
        this.bodyMain1.setTexSize(512, 512);
        this.bodyMain1.mirror = true;
        this.setRotation(this.bodyMain1, 0F, -0.3926991F, 0F);
        this.bodyMain2 = new ModelPart(this, 125, 128);
        this.bodyMain2.addBox(-24F, -0.1F, -24F, 48, 12, 48);
        this.bodyMain2.setPos(0F, 0F, 0F);
        this.bodyMain2.setTexSize(512, 512);
        this.bodyMain2.mirror = true;
        this.setRotation(this.bodyMain2, 0F, 0.3926991F, 0F);
        this.bodyAux1 = new ModelPart(this, 125, 192);
        this.bodyAux1.addBox(-13F, -5F, -34F, 26, 15, 34);
        this.bodyAux1.setPos(0F, 0F, 0F);
        this.bodyAux1.setTexSize(512, 512);
        this.bodyAux1.mirror = true;
        this.setRotation(this.bodyAux1, 0.0698132F, 0.7853982F, 0F);
        this.bodyAux2 = new ModelPart(this, 125, 192);
        this.bodyAux2.addBox(-13F, -5F, -34F, 26, 15, 34);
        this.bodyAux2.setPos(0F, 0F, 0F);
        this.bodyAux2.setTexSize(512, 512);
        this.bodyAux2.mirror = true;
        this.setRotation(this.bodyAux2, 0.0698132F, -0.7853982F, 0F);
        this.bodyAux3 = new ModelPart(this, 125, 192);
        this.bodyAux3.addBox(-13F, -5F, -34F, 26, 15, 34);
        this.bodyAux3.setPos(0F, 0F, 0F);
        this.bodyAux3.setTexSize(512, 512);
        this.bodyAux3.mirror = true;
        this.setRotation(this.bodyAux3, 0.0698132F, -2.356194F, 0F);
        this.bodyAux4 = new ModelPart(this, 125, 192);
        this.bodyAux4.addBox(-13F, -5F, -34F, 26, 15, 34);
        this.bodyAux4.setPos(0F, 0F, 0F);
        this.bodyAux4.setTexSize(512, 512);
        this.bodyAux4.mirror = true;
        this.setRotation(this.bodyAux4, 0.0698132F, 2.356194F, 0F);
        this.bodySupportMain1 = new ModelPart(this, 125, 243);
        this.bodySupportMain1.addBox(-19F, -7F, -19F, 38, 10, 38);
        this.bodySupportMain1.setPos(0F, 0F, 0F);
        this.bodySupportMain1.setTexSize(512, 512);
        this.bodySupportMain1.mirror = true;
        this.setRotation(this.bodySupportMain1, 0F, 0F, 0F);
        this.bodySupportMain2 = new ModelPart(this, 125, 293);
        this.bodySupportMain2.addBox(-12F, -15F, -12F, 24, 15, 24);
        this.bodySupportMain2.setPos(0F, 0F, 0F);
        this.bodySupportMain2.setTexSize(512, 512);
        this.bodySupportMain2.mirror = true;
        this.setRotation(this.bodySupportMain2, 0F, 0F, 0F);
        this.legStructureAux1e = new ModelPart(this, 0, 81);
        this.legStructureAux1e.addBox(-26F, 28F, -2F, 3, 24, 3);
        this.legStructureAux1e.setPos(0F, 0F, 0F);
        this.legStructureAux1e.setTexSize(512, 512);
        this.legStructureAux1e.mirror = true;
        this.setRotation(this.legStructureAux1e, 0F, -2.356194F, 0.7330383F);
        this.footBaseg = new ModelPart(this, 125, 81);
        this.footBaseg.addBox(-64F, 18.9F, -2F, 11, 6, 2);
        this.footBaseg.setPos(0F, 0F, 0F);
        this.footBaseg.setTexSize(512, 512);
        this.footBaseg.mirror = true;
        this.setRotation(this.footBaseg, 0F, -2.356194F, 0F);
        this.legStructure1i = new ModelPart(this, 0, 0);
        this.legStructure1i.addBox(-27F, 33F, -3F, 5, 14, 5);
        this.legStructure1i.setPos(0F, 0F, 0F);
        this.legStructure1i.setTexSize(512, 512);
        this.legStructure1i.mirror = true;
        this.setRotation(this.legStructure1i, 0F, -2.356194F, 0.7330383F);
        this.legBase1j = new ModelPart(this, 16, 81);
        this.legBase1j.addBox(-29F, 20F, -5F, 9, 10, 9);
        this.legBase1j.setPos(0F, 0F, 0F);
        this.legBase1j.setTexSize(512, 512);
        this.legBase1j.mirror = true;
        this.setRotation(this.legBase1j, 0F, -2.356194F, 0.7330383F);
        this.footJointk = new ModelPart(this, 64, 104);
        this.footJointk.addBox(-58F, 20F, -6.5F, 7, 5, 11);
        this.footJointk.setPos(0F, 0F, 0F);
        this.footJointk.setTexSize(512, 512);
        this.footJointk.mirror = true;
        this.setRotation(this.footJointk, 0F, -2.356194F, 0F);
        this.footBasel = new ModelPart(this, 64, 81);
        this.footBasel.addBox(-62F, 23F, -8.5F, 10, 2, 15);
        this.footBasel.setPos(0F, 0F, 0F);
        this.footBasel.setTexSize(512, 512);
        this.footBasel.mirror = true;
        this.setRotation(this.footBasel, 0F, -2.356194F, 0F);
        this.legBase1n = new ModelPart(this, 16, 81);
        this.legBase1n.addBox(-29F, 20F, -5F, 9, 10, 9);
        this.legBase1n.setPos(0F, 0F, 0F);
        this.legBase1n.setTexSize(512, 512);
        this.legBase1n.mirror = true;
        this.setRotation(this.legBase1n, 0F, -0.7853982F, 0.7330383F);
        this.legStructureAux1s = new ModelPart(this, 0, 81);
        this.legStructureAux1s.addBox(-26F, 28F, -2F, 3, 24, 3);
        this.legStructureAux1s.setPos(0F, 0F, 0F);
        this.legStructureAux1s.setTexSize(512, 512);
        this.legStructureAux1s.mirror = true;
        this.setRotation(this.legStructureAux1s, 0F, -0.7853982F, 0.7330383F);
        this.legStructure1u = new ModelPart(this, 0, 0);
        this.legStructure1u.addBox(-27F, 33F, -3F, 5, 14, 5);
        this.legStructure1u.setPos(0F, 0F, 0F);
        this.legStructure1u.setTexSize(512, 512);
        this.legStructure1u.mirror = true;
        this.setRotation(this.legStructure1u, 0F, -0.7853982F, 0.7330383F);
        this.footJointv = new ModelPart(this, 64, 104);
        this.footJointv.addBox(-58F, 20F, -6.5F, 7, 5, 11);
        this.footJointv.setPos(0F, 0F, 0F);
        this.footJointv.setTexSize(512, 512);
        this.footJointv.mirror = true;
        this.setRotation(this.footJointv, 0F, -0.7853982F, 0F);
        this.footBasew = new ModelPart(this, 64, 81);
        this.footBasew.addBox(-62F, 23F, -8.5F, 10, 2, 15);
        this.footBasew.setPos(0F, 0F, 0F);
        this.footBasew.setTexSize(512, 512);
        this.footBasew.mirror = true;
        this.setRotation(this.footBasew, 0F, -0.7853982F, 0F);
        this.footBasex = new ModelPart(this, 125, 81);
        this.footBasex.addBox(-64F, 18.9F, -2F, 11, 6, 2);
        this.footBasex.setPos(0F, 0F, 0F);
        this.footBasex.setTexSize(512, 512);
        this.footBasex.mirror = true;
        this.setRotation(this.footBasex, 0F, -0.7853982F, 0F);
        this.legBase1z = new ModelPart(this, 16, 81);
        this.legBase1z.addBox(-29F, 20F, -5F, 9, 10, 9);
        this.legBase1z.setPos(0F, 0F, 0F);
        this.legBase1z.setTexSize(512, 512);
        this.legBase1z.mirror = true;
        this.setRotation(this.legBase1z, 0F, 0.7853982F, 0.7330383F);
        this.legStructureAux1f = new ModelPart(this, 0, 81);
        this.legStructureAux1f.addBox(-26F, 28F, -2F, 3, 24, 3);
        this.legStructureAux1f.setPos(0F, 0F, 0F);
        this.legStructureAux1f.setTexSize(512, 512);
        this.legStructureAux1f.mirror = true;
        this.setRotation(this.legStructureAux1f, 0F, 0.7853982F, 0.7330383F);
        this.legStructure1g = new ModelPart(this, 0, 0);
        this.legStructure1g.addBox(-27F, 33F, -3F, 5, 14, 5);
        this.legStructure1g.setPos(0F, 0F, 0F);
        this.legStructure1g.setTexSize(512, 512);
        this.legStructure1g.mirror = true;
        this.setRotation(this.legStructure1g, 0F, 0.7853982F, 0.7330383F);
        this.footJointh = new ModelPart(this, 64, 104);
        this.footJointh.addBox(-58F, 20F, -6.5F, 7, 5, 11);
        this.footJointh.setPos(0F, 0F, 0F);
        this.footJointh.setTexSize(512, 512);
        this.footJointh.mirror = true;
        this.setRotation(this.footJointh, 0F, 0.7853982F, 0F);
        this.footBasei = new ModelPart(this, 64, 81);
        this.footBasei.addBox(-62F, 23F, -8.5F, 10, 2, 15);
        this.footBasei.setPos(0F, 0F, 0F);
        this.footBasei.setTexSize(512, 512);
        this.footBasei.mirror = true;
        this.setRotation(this.footBasei, 0F, 0.7853982F, 0F);
        this.footBasej = new ModelPart(this, 125, 81);
        this.footBasej.addBox(-64F, 18.9F, -2F, 11, 6, 2);
        this.footBasej.setPos(0F, 0F, 0F);
        this.footBasej.setTexSize(512, 512);
        this.footBasej.mirror = true;
        this.setRotation(this.footBasej, 0F, 0.7853982F, 0F);
        this.legBase1l = new ModelPart(this, 16, 81);
        this.legBase1l.addBox(-29F, 20F, -5F, 9, 10, 9);
        this.legBase1l.setPos(0F, 0F, 0F);
        this.legBase1l.setTexSize(512, 512);
        this.legBase1l.mirror = true;
        this.setRotation(this.legBase1l, 0F, 2.356194F, 0.7330383F);
        this.legStructureAux1q = new ModelPart(this, 0, 81);
        this.legStructureAux1q.addBox(-26F, 28F, -2F, 3, 24, 3);
        this.legStructureAux1q.setPos(0F, 0F, 0F);
        this.legStructureAux1q.setTexSize(512, 512);
        this.legStructureAux1q.mirror = true;
        this.setRotation(this.legStructureAux1q, 0F, 2.356194F, 0.7330383F);
        this.legStructure1s = new ModelPart(this, 0, 0);
        this.legStructure1s.addBox(-27F, 33F, -3F, 5, 14, 5);
        this.legStructure1s.setPos(0F, 0F, 0F);
        this.legStructure1s.setTexSize(512, 512);
        this.legStructure1s.mirror = true;
        this.setRotation(this.legStructure1s, 0F, 2.356194F, 0.7330383F);
        this.footJointt = new ModelPart(this, 64, 104);
        this.footJointt.addBox(-58F, 20F, -6.5F, 7, 5, 11);
        this.footJointt.setPos(0F, 0F, 0F);
        this.footJointt.setTexSize(512, 512);
        this.footJointt.mirror = true;
        this.setRotation(this.footJointt, 0F, 2.356194F, 0F);
        this.footBaseu = new ModelPart(this, 64, 81);
        this.footBaseu.addBox(-62F, 23F, -8.5F, 10, 2, 15);
        this.footBaseu.setPos(0F, 0F, 0F);
        this.footBaseu.setTexSize(512, 512);
        this.footBaseu.mirror = true;
        this.setRotation(this.footBaseu, 0F, 2.356194F, 0F);
        this.footBasev = new ModelPart(this, 125, 81);
        this.footBasev.addBox(-64F, 18.9F, -2F, 11, 6, 2);
        this.footBasev.setPos(0F, 0F, 0F);
        this.footBasev.setTexSize(512, 512);
        this.footBasev.mirror = true;
        this.setRotation(this.footBasev, 0F, 2.356194F, 0F);
        this.moduleMain1 = new ModelPart(this, 289, 166);
        this.moduleMain1.addBox(-6F, -44F, -16F, 32, 32, 32);
        this.moduleMain1.setPos(0F, 0F, 0F);
        this.moduleMain1.setTexSize(512, 512);
        this.moduleMain1.mirror = true;
        this.setRotation(this.moduleMain1, 0F, 0F, 0F);
        this.moduleMain2 = new ModelPart(this, 249, 234);
        this.moduleMain2.addBox(-16F, -30F, -13F, 32, 16, 26);
        this.moduleMain2.setPos(0F, 0F, 0F);
        this.moduleMain2.setTexSize(512, 512);
        this.moduleMain2.mirror = true;
        this.setRotation(this.moduleMain2, 0F, 0F, 0F);
        this.moduleMainDeco = new ModelPart(this, 70, 300);
        this.moduleMainDeco.addBox(16F, -46F, -20.9F, 5, 30, 37);
        this.moduleMainDeco.setPos(0F, 0F, 0F);
        this.moduleMainDeco.setTexSize(512, 512);
        this.moduleMainDeco.mirror = true;
        this.setRotation(this.moduleMainDeco, 0F, 0F, 0F);
        this.moduleCore1 = new ModelPart(this, 197, 293);
        this.moduleCore1.mirror = true;
        this.moduleCore1.addBox(-20F, -48F, -25F, 5, 36, 42);
        this.moduleCore1.setPos(0F, 0F, 0F);
        this.moduleCore1.setTexSize(512, 512);
        this.moduleCore1.mirror = true;
        this.setRotation(this.moduleCore1, 0F, 0F, 0F);
        this.moduleCore1.mirror = false;
        this.moduleCore2 = new ModelPart(this, 197, 293);
        this.moduleCore2.addBox(-30F, -48F, -25F, 5, 36, 42);
        this.moduleCore2.setPos(0F, 0F, 0F);
        this.moduleCore2.setTexSize(512, 512);
        this.moduleCore2.mirror = true;
        this.setRotation(this.moduleCore2, 0F, 0F, 0F);
        this.moduleCore3 = new ModelPart(this, 295, 300);
        this.moduleCore3.addBox(-25F, -44F, -21F, 5, 28, 34);
        this.moduleCore3.setPos(0F, 0F, 0F);
        this.moduleCore3.setTexSize(512, 512);
        this.moduleCore3.mirror = true;
        this.setRotation(this.moduleCore3, 0F, 0F, 0F);
        this.moduleAux1 = new ModelPart(this, 85, 238);
        this.moduleAux1.addBox(26F, -34F, -13F, 4, 16, 26);
        this.moduleAux1.setPos(0F, 0F, 0F);
        this.moduleAux1.setTexSize(512, 512);
        this.moduleAux1.mirror = true;
        this.setRotation(this.moduleAux1, 0F, 0F, 0F);
        this.moduleAux2 = new ModelPart(this, 0, 110);
        this.moduleAux2.addBox(26.1F, -60F, 9F, 1, 28, 1);
        this.moduleAux2.setPos(0F, 0F, 0F);
        this.moduleAux2.setTexSize(512, 512);
        this.moduleAux2.mirror = true;
        this.setRotation(this.moduleAux2, 0F, 0F, 0F);
        this.moduleAux3 = new ModelPart(this, 17, 110);
        this.moduleAux3.addBox(27F, -57F, -12F, 1, 23, 1);
        this.moduleAux3.setPos(0F, 0F, 0F);
        this.moduleAux3.setTexSize(512, 512);
        this.moduleAux3.mirror = true;
        this.setRotation(this.moduleAux3, 0F, 0F, 0F);
        this.moduleAux4 = new ModelPart(this, 5, 110);
        this.moduleAux4.addBox(28F, -48F, -8F, 1, 14, 1);
        this.moduleAux4.setPos(0F, 0F, 0F);
        this.moduleAux4.setTexSize(512, 512);
        this.moduleAux4.mirror = true;
        this.setRotation(this.moduleAux4, 0F, 0F, 0F);
        this.moduleAux5 = new ModelPart(this, 5, 110);
        this.moduleAux5.addBox(28F, -48F, -6F, 1, 14, 1);
        this.moduleAux5.setPos(0F, 0F, 0F);
        this.moduleAux5.setTexSize(512, 512);
        this.moduleAux5.mirror = true;
        this.setRotation(this.moduleAux5, 0F, 0F, 0F);
        this.moduleAux6 = new ModelPart(this, 5, 110);
        this.moduleAux6.addBox(28F, -48F, -4F, 1, 14, 1);
        this.moduleAux6.setPos(0F, 0F, 0F);
        this.moduleAux6.setTexSize(512, 512);
        this.moduleAux6.mirror = true;
        this.setRotation(this.moduleAux6, 0F, 0F, 0F);
        this.moduleAux7 = new ModelPart(this, 11, 110);
        this.moduleAux7.addBox(27F, -53F, 2F, 1, 19, 1);
        this.moduleAux7.setPos(0F, 0F, 0F);
        this.moduleAux7.setTexSize(512, 512);
        this.moduleAux7.mirror = true;
        this.setRotation(this.moduleAux7, 0F, 0F, 0F);
        this.moduleAux8 = new ModelPart(this, 0, 110);
        this.moduleAux8.addBox(28.9F, -60F, 9F, 1, 28, 1);
        this.moduleAux8.setPos(0F, 0F, 0F);
        this.moduleAux8.setTexSize(512, 512);
        this.moduleAux8.mirror = true;
        this.setRotation(this.moduleAux8, 0F, 0F, 0F);
        this.moduleThrusterBase1 = new ModelPart(this, 21, 19);
        this.moduleThrusterBase1.addBox(30F, -28F, -5F, 3, 6, 10);
        this.moduleThrusterBase1.setPos(0F, 0F, 0F);
        this.moduleThrusterBase1.setTexSize(512, 512);
        this.moduleThrusterBase1.mirror = true;
        this.setRotation(this.moduleThrusterBase1, 0F, 0F, 0F);
        this.moduleThrusterBase2 = new ModelPart(this, 50, 0);
        this.moduleThrusterBase2.addBox(5F, -39F, -5F, 3, 5, 10);
        this.moduleThrusterBase2.setPos(0F, 0F, 0F);
        this.moduleThrusterBase2.setTexSize(512, 512);
        this.moduleThrusterBase2.mirror = true;
        this.setRotation(this.moduleThrusterBase2, 0F, 0F, 0.7853982F);
        this.moduleThrusterBase3 = new ModelPart(this, 50, 0);
        this.moduleThrusterBase3.addBox(-43F, -4F, -5F, 3, 6, 10);
        this.moduleThrusterBase3.setPos(0F, 0F, 0F);
        this.moduleThrusterBase3.setTexSize(512, 512);
        this.moduleThrusterBase3.mirror = true;
        this.setRotation(this.moduleThrusterBase3, 0F, 0F, 2.356194F);
        this.moduleThrusterBase4 = new ModelPart(this, 0, 23);
        this.moduleThrusterBase4.addBox(24F, -28F, 13.7F, 3, 6, 6);
        this.moduleThrusterBase4.setPos(0F, 0F, 0F);
        this.moduleThrusterBase4.setTexSize(512, 512);
        this.moduleThrusterBase4.mirror = true;
        this.setRotation(this.moduleThrusterBase4, 0F, 0.7853982F, 0F);
        this.moduleThrusterBase5 = new ModelPart(this, 0, 23);
        this.moduleThrusterBase5.mirror = true;
        this.moduleThrusterBase5.addBox(24F, -28F, -19.5F, 3, 6, 6);
        this.moduleThrusterBase5.setPos(0F, 0F, 0F);
        this.moduleThrusterBase5.setTexSize(512, 512);
        this.moduleThrusterBase5.mirror = true;
        this.setRotation(this.moduleThrusterBase5, 0F, -0.7853982F, 0F);
        this.moduleThrusterBase5.mirror = false;
        this.moduleThrusterConeTop1 = new ModelPart(this, 40, 9);
        this.moduleThrusterConeTop1.addBox(-44F, -3F, -1F, 1, 2, 2);
        this.moduleThrusterConeTop1.setPos(0F, 0F, 0F);
        this.moduleThrusterConeTop1.setTexSize(512, 512);
        this.moduleThrusterConeTop1.mirror = true;
        this.setRotation(this.moduleThrusterConeTop1, 0F, 0F, 2.356194F);
        this.moduleThrusterConeTop2 = new ModelPart(this, 30, 9);
        this.moduleThrusterConeTop2.addBox(-45F, -3.5F, -1.5F, 1, 3, 3);
        this.moduleThrusterConeTop2.setPos(0F, 0F, 0F);
        this.moduleThrusterConeTop2.setTexSize(512, 512);
        this.moduleThrusterConeTop2.mirror = true;
        this.setRotation(this.moduleThrusterConeTop2, 0F, 0F, 2.356194F);
        this.moduleThrusterConeTop3 = new ModelPart(this, 29, 0);
        this.moduleThrusterConeTop3.addBox(-46F, -4F, -2F, 1, 4, 4);
        this.moduleThrusterConeTop3.setPos(0F, 0F, 0F);
        this.moduleThrusterConeTop3.setTexSize(512, 512);
        this.moduleThrusterConeTop3.mirror = true;
        this.setRotation(this.moduleThrusterConeTop3, 0F, 0F, 2.356194F);
        this.moduleThrusterConeBack1 = new ModelPart(this, 40, 9);
        this.moduleThrusterConeBack1.addBox(27F, -26F, -18.5F, 1, 2, 2);
        this.moduleThrusterConeBack1.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBack1.setTexSize(512, 512);
        this.moduleThrusterConeBack1.mirror = true;
        this.setRotation(this.moduleThrusterConeBack1, 0F, -0.7853982F, 0F);
        this.moduleThrusterConeBack2 = new ModelPart(this, 30, 9);
        this.moduleThrusterConeBack2.addBox(28F, -26.5F, -19F, 1, 3, 3);
        this.moduleThrusterConeBack2.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBack2.setTexSize(512, 512);
        this.moduleThrusterConeBack2.mirror = true;
        this.setRotation(this.moduleThrusterConeBack2, 0F, -0.7853982F, 0F);
        this.moduleThrusterConeBack3 = new ModelPart(this, 29, 0);
        this.moduleThrusterConeBack3.addBox(29F, -27F, -19.5F, 1, 4, 4);
        this.moduleThrusterConeBack3.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBack3.setTexSize(512, 512);
        this.moduleThrusterConeBack3.mirror = true;
        this.setRotation(this.moduleThrusterConeBack3, 0F, -0.7853982F, 0F);
        this.moduleThrusterConeBot1 = new ModelPart(this, 40, 9);
        this.moduleThrusterConeBot1.addBox(8F, -38F, -1F, 1, 2, 2);
        this.moduleThrusterConeBot1.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBot1.setTexSize(512, 512);
        this.moduleThrusterConeBot1.mirror = true;
        this.setRotation(this.moduleThrusterConeBot1, 0F, 0F, 0.7853982F);
        this.moduleThrusterConeBot2 = new ModelPart(this, 30, 9);
        this.moduleThrusterConeBot2.addBox(9F, -38.5F, -1.5F, 1, 3, 3);
        this.moduleThrusterConeBot2.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBot2.setTexSize(512, 512);
        this.moduleThrusterConeBot2.mirror = true;
        this.setRotation(this.moduleThrusterConeBot2, 0F, 0F, 0.7853982F);
        this.moduleThrusterConeBot3 = new ModelPart(this, 29, 0);
        this.moduleThrusterConeBot3.addBox(10F, -39F, -2F, 1, 4, 4);
        this.moduleThrusterConeBot3.setPos(0F, 0F, 0F);
        this.moduleThrusterConeBot3.setTexSize(512, 512);
        this.moduleThrusterConeBot3.mirror = true;
        this.setRotation(this.moduleThrusterConeBot3, 0F, 0F, 0.7853982F);
        this.moduleThrusterConeFront1 = new ModelPart(this, 40, 9);
        this.moduleThrusterConeFront1.addBox(27F, -26F, 16.5F, 1, 2, 2);
        this.moduleThrusterConeFront1.setPos(0F, 0F, 0F);
        this.moduleThrusterConeFront1.setTexSize(512, 512);
        this.moduleThrusterConeFront1.mirror = true;
        this.setRotation(this.moduleThrusterConeFront1, 0F, 0.7853982F, 0F);
        this.moduleThrusterConeFront2 = new ModelPart(this, 30, 9);
        this.moduleThrusterConeFront2.addBox(28F, -26.5F, 16F, 1, 3, 3);
        this.moduleThrusterConeFront2.setPos(0F, 0F, 0F);
        this.moduleThrusterConeFront2.setTexSize(512, 512);
        this.moduleThrusterConeFront2.mirror = true;
        this.setRotation(this.moduleThrusterConeFront2, 0F, 0.7853982F, 0F);
        this.moduleThrusterConeFront3 = new ModelPart(this, 29, 0);
        this.moduleThrusterConeFront3.addBox(29F, -27F, 15.5F, 1, 4, 4);
        this.moduleThrusterConeFront3.setPos(0F, 0F, 0F);
        this.moduleThrusterConeFront3.setTexSize(512, 512);
        this.moduleThrusterConeFront3.mirror = true;
        this.setRotation(this.moduleThrusterConeFront3, 0F, 0.7853982F, 0F);
    }

    @Override
    public void setRotationAngles(EntityLander entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {
        this.bodyMain1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodyMain2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodyAux1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodyAux2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodyAux3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodyAux4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodySupportMain1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.bodySupportMain2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructureAux1e.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBaseg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructure1i.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legBase1j.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footJointk.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasel.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legBase1n.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructureAux1s.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructure1u.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footJointv.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasew.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasex.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legBase1z.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructureAux1f.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructure1g.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footJointh.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasei.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasej.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legBase1l.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructureAux1q.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.legStructure1s.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footJointt.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBaseu.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.footBasev.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleMain1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleMain2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleMainDeco.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleCore1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleCore2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleCore3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleAux8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterBase1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterBase2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterBase3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterBase4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterBase5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeTop1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeTop2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeTop3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBack1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBack2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBack3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBot1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBot2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeBot3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeFront1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeFront2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        this.moduleThrusterConeFront3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    private void setRotation(ModelPart model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}
