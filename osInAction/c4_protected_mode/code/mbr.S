;主引导程序
;---------------------------------------------
%include "boot.inc"
SECTION MBR vstart=0x7c00
	mov ax,cs
	mov ds,ax
	mov es,ax
	mov ss,ax
	mov fs,ax
	mov sp,0x7c00
	mov ax,0xb800
	mov gs,ax
	
;清屏
;利用0x06号功能，上卷全部行，则可清屏。
; -----------------------------------------------------------
;INT 0x10   功能号:0x06	   功能描述:上卷窗口
;------------------------------------------------------
;输入：
;AH 功能号= 0x06
;AL = 上卷的行数(如果为0,表示全部)
;BH = 上卷行属性
;(CL,CH) = 窗口左上角的(X,Y)位置
;(DL,DH) = 窗口右下角的(X,Y)位置
;无返回值：
mov ax, 0600h
mov bx, 0700h
mov cx, 0	;左上角: (0, 0)
mov dx, 184fh	;右下角: (80,25)
		; 因为VGA文本模式中，一行只能容纳80个字符,共25行。
	 	; 下标从0开始，所以0x18=24,0x4f=79
int 10h		;int 10h

;输入字符串：MBR
mov byte [gs:0x00], '1'
mov byte [gs:0x01], 0xA4

mov byte [gs:0x02], ' '
mov byte [gs:0x03], 0xA4

mov byte [gs:0x04], 'M'
mov byte [gs:0x05], 0xA4

mov byte [gs:0x06], 'B'
mov byte [gs:0x07], 0xA4

mov byte [gs:0x08], 'R'
mov byte [gs:0x09], 0xA4

mov eax, LOADER_START_SECTOR	;起始扇区lba地址
mov bx, LOADER_BASE_ADDR	;写入的地址
mov cx, 4			;待读入的扇区数
call rd_disk_m_16		;以下是读取程序的起始部分

jmp LOADER_BASE_ADDR

;-------------------------------------------------------------------------------
;功能:读取硬盘n个扇区.在16位模式下读硬盘
rd_disk_m_16:	   
;-------------------------------------------------------------------------------
				       ; eax=LBA扇区号
				       ; ebx=将数据写入的内存地址
				       ; ecx=读入的扇区数
mov esi, eax			;备份eax,因为al在out指令中会被用到
mov di, cx			;备份cx,cx在读取数据时会被用到
;读写硬盘	
;第一步：设置要读取的扇区数
mov dx, 0x1f2
mov al, cl			;读入cl的低8位,即读取上面写入ax的扇区数
out dx, al			;读取的扇区数。写入要读取的扇区数到dx存储的地址中

mov eax, esi			;恢复ax

;第2步：将LBA地址存入0x1f3 ~ 0x1f6

;LBA地址7~0位写入端口0x1f3
mov dx, 0x1f3
out dx, al

;LBA地址15~8位写入端口0x1f4
mov cl, 8
shr eax, cl			;逻辑右移cl位
mov dx, 0x1f4
out dx, al

;LBA地址23~16位写入端口0x1f5
shr eax, cl			;继续逻辑右移cl位
mov dx, 0x1f5
out dx, al

shr eax, cl			;逻辑右移，置0
and al, 0x0f			;lba第24-27位
or al, 0xe0			;设置7～4位为1110，表示lba模式
mov dx, 0x1f6
out dx, al

;第3步：向0x1f7端口写入读命令，0x20 
mov dx, 0x1f7
mov al, 0x20
out dx, al

;第4步：检测硬盘状态
  .not_ready:
      ;同一端口，写时表示写入命令字，读时表示读入硬盘状态
	nop		;表示空操作
	in al, dx	;第4位为1表示硬盘控制器已准备好数据传输，第7位为1表示硬盘忙
	and al, 0x88
	cmp al, 0x08
	jnz .not_ready	;若未准备好，继续等。

;第5步：从0x1f0端口读数据
	mov ax, di
	mov dx, 256
	mul dx
	mov cx, ax	; di为要读取的扇区数，一个扇区有512字节，每次读入一个字，
			   ; 共需di*512/2次，所以di*256
	mov dx, 0x1f0
  .go_on_ready:
	in ax, dx
	mov [bx], ax
	add bx, 2
	loop .go_on_ready
	ret

times 510-($-$$) db 0
db 0x55, 0xaa
























