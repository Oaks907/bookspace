这是《操作系统真相还原》一书中的示例代码测试。

```$java
nasm -I include/ -o loader.bin loader.S
nasm -I include/ -o mbr.bin  mbr.S

bximage -hd -mode="flat" -size=60 -q hd60M.img

dd if=/home/haifei/software/bookspace/osInAction/c5_page/code/mbr.bin of=hd60M.img bs=512 count=1 conv=notrunc

dd if=/home/haifei/software/bookspace/osInAction/c5_page/code/loader.bin of=hd60M.img bs=512 count=4 seek=2 conv=notrunc

bochs -f .bochsrc 
```
