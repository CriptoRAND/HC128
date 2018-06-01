package ar.edu.unlam.cripto.parser;



public class ideone_xkHH3j
{
	public static void main (String[] args) throws java.lang.Exception
	{
		String iv_srt = "@#$$54214AEFDCAE";
        String key_srt = "AAAAAAAAqweAAAAT";
		 ideone_xkHH3j hc_enc = new ideone_xkHH3j(iv_srt.getBytes(), key_srt.getBytes());

        String s = "da";
      
        byte[] ed = encrypt(hc_enc, s.getBytes());
        
        hc_enc.reset();

        byte[] ed33 = encrypt(hc_enc, ed);
        
        System.out.println(new String(ed33));
	}
	
	public static byte[] encrypt(ideone_xkHH3j hc, byte[] data) {

        for (int i = 0; i < data.length; i++) {
            data[i] = hc.returnByte(data[i]);
        }
        return data;
    }
    
	private static int f1(int x) {
        return Integer.rotateRight(x, 7) ^ Integer.rotateRight(x, 18) ^ (x >>> 3);
    }

    private static int f2(int x) {
        return Integer.rotateRight(x, 17) ^ Integer.rotateRight(x, 19) ^ (x >>> 10);
    }

    private int g1(int x, int y, int z) {
        return (Integer.rotateRight(x, 10) ^ Integer.rotateRight(z, 23))
                + Integer.rotateRight(y, 8);
    }

    private int g2(int x, int y, int z) {
        return (Integer.rotateLeft(x, 10) ^ Integer.rotateLeft(z, 23))
                + Integer.rotateLeft(y, 8);
    }

    private int h1(int x) {
        return q[x & 0xFF] + q[((x >> 16) & 0xFF) + 256];
    }

    private int h2(int x) {
        return p[x & 0xFF] + p[((x >> 16) & 0xFF) + 256];
    }

    private static int mod1024(int x) {
        return x & 0x3FF;
    }

    private static int mod512(int x) {
        return x & 0x1FF;
    }

    private static int dim(int x, int y) {
        return mod512(x - y);
    }

    private int step() {
        int j = mod512(cnt);
        int ret;
        if (cnt < 512) {
            p[j] += g1(p[dim(j, 3)], p[dim(j, 10)], p[dim(j, 511)]);
            ret = h1(p[dim(j, 12)]) ^ p[j];
        } else {
            q[j] += g2(q[dim(j, 3)], q[dim(j, 10)], q[dim(j, 511)]);
            ret = h2(q[dim(j, 12)]) ^ q[j];
        }
        cnt = mod1024(cnt + 1);
       
        return ret;
    }
    
    private byte[] key, iv;
    private int[] p = new int[512];
    private int[] q = new int[512];
    private int cnt = 0;
    private byte[] buf = new byte[4];
    private int idx = 0;

    public int getCnt() {
        return cnt;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ideone_xkHH3j(byte[] iv, byte[] key) {
        this.iv = iv;
        this.key = key;
        init();
    }

    private void init() {
        if (key.length != 16) {
            throw new java.lang.IllegalArgumentException(
                    "The key must be 128 bit long");
        }
        cnt = 0;
        int[] w = new int[1280];
        // El parentesis cicla la i entre 0 y 7 para hacer el shift
        for (int i = 0; i < 16; i++) {
            w[i >> 3] |= key[i] << (i & 0x7);
        }
        System.arraycopy(w, 0, w, 4, 4);

        for (int i = 0; i < Math.min(16, iv.length); i++) {
            w[(i >> 3) + 8] |= iv[i] << (i & 0x7);
        }
        System.arraycopy(w, 8, w, 12, 4);

        for (int i = 16; i < 1280; i++) {
            w[i] = f2(w[i - 2]) + w[i - 7] + f1(w[i - 15]) + w[i - 16] + i;
        }

        System.arraycopy(w, 256, p, 0, 512);
        System.arraycopy(w, 768, q, 0, 512);

        for (int i = 0; i < 512; i++) {
            p[i] = step();
        }
        for (int i = 0; i < 512; i++) {
            q[i] = step();
        }

        cnt = 0;
    }

    private byte getByte() {
        if (idx == 0) {
            int step = step();
            buf[3] = (byte) (step & 0xFF);
            step >>= 8;
        	buf[2] = (byte) (step & 0xFF);
            step >>= 8;
        	buf[1] = (byte) (step & 0xFF);
            step >>= 8;
            buf[0] = (byte) (step & 0xFF);
        }
        byte ret = buf[idx];
        idx = idx + 1 & 0x3;
        return ret;
    }

    public void reset() {
        idx = 0;
        init();
    }

    public byte returnByte(byte in) {
        return (byte) (in ^ getByte());
    }
}