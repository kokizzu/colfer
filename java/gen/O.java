package gen;


// Code generated by colf(1); DO NOT EDIT.
// The compiler used schema file test.colf.


import static java.lang.String.format;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;


/**
 * Data bean with built-in serialization support.
 * O contains all supported data types.
 * @author generated by colf(1)
 * @see <a href="https://github.com/pascaldekloe/colfer">Colfer's home</a>
 */
public class O implements Serializable {

	/** The upper limit for serial byte sizes. */
	public static int colferSizeMax = 16 * 1024 * 1024;

	/** The upper limit for the number of elements in a list. */
	public static int colferListMax = 64 * 1024;


	/**
	 * B tests booleans.
	 */
	public boolean b;

	/**
	 * U32 tests unsigned 32-bit integers.
	 */
	public int u32;

	/**
	 * U64 tests unsigned 64-bit integers.
	 */
	public long u64;

	/**
	 * I32 tests signed 32-bit integers.
	 */
	public int i32;

	/**
	 * I64 tests signed 64-bit integers.
	 */
	public long i64;

	/**
	 * F32 tests 32-bit floating points.
	 */
	public float f32;

	/**
	 * F64 tests 64-bit floating points.
	 */
	public double f64;

	/**
	 * T tests timestamps.
	 */
	public java.time.Instant t;

	/**
	 * S tests text.
	 */
	public String s;

	/**
	 * A tests binaries.
	 */
	public byte[] a;

	/**
	 * O tests nested data structures.
	 */
	public O o;

	/**
	 * Os tests data structure lists.
	 */
	public O[] os;

	/**
	 * Ss tests text lists.
	 */
	public String[] ss;

	/**
	 * As tests binary lists.
	 */
	public byte[][] as;

	/**
	 * U8 tests unsigned 8-bit integers.
	 */
	public byte u8;

	/**
	 * U16 tests unsigned 16-bit integers.
	 */
	public short u16;

	/**
	 * F32s tests 32-bit floating point lists.
	 */
	public float[] f32s;

	/**
	 * F64s tests 64-bit floating point lists.
	 */
	public double[] f64s;

	/** Default constructor */
	public O() {
		init();
	}

	private static final byte[] _zeroBytes = new byte[0];
	private static final byte[][] _zeroBinaries = new byte[0][];
	private static final O[] _zeroOs = new O[0];
	private static final String[] _zeroSs = new String[0];
	private static final float[] _zeroF32s = new float[0];
	private static final double[] _zeroF64s = new double[0];

	/** Colfer zero values. */
	private void init() {
		s = "";
		a = _zeroBytes;
		os = _zeroOs;
		ss = _zeroSs;
		as = _zeroBinaries;
		f32s = _zeroF32s;
		f64s = _zeroF64s;
	}

	/**
	 * {@link #reset(InputStream) Reusable} deserialization of Colfer streams.
	 */
	public static class Unmarshaller {

		/** The data source. */
		protected InputStream in;

		/** The read buffer. */
		public byte[] buf;

		/** The {@link #buf buffer}'s data start index, inclusive. */
		protected int offset;

		/** The {@link #buf buffer}'s data end index, exclusive. */
		protected int i;


		/**
		 * @param in the data source or {@code null}.
		 * @param buf the initial buffer or {@code null}.
		 */
		public Unmarshaller(InputStream in, byte[] buf) {
			// TODO: better size estimation
			if (buf == null || buf.length == 0)
				buf = new byte[Math.min(O.colferSizeMax, 2048)];
			this.buf = buf;
			reset(in);
		}

		/**
		 * Reuses the marshaller.
		 * @param in the data source or {@code null}.
		 * @throws IllegalStateException on pending data.
		 */
		public void reset(InputStream in) {
			if (this.i != this.offset) throw new IllegalStateException("colfer: pending data");
			this.in = in;
			this.offset = 0;
			this.i = 0;
		}

		/**
		 * Deserializes the following object.
		 * @return the result or {@code null} when EOF.
		 * @throws IOException from the input stream.
		 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
		 * @throws InputMismatchException when the data does not match this object's schema.
		 */
		public O next() throws IOException {
			if (in == null) return null;

			while (true) {
				if (this.i > this.offset) {
					try {
						O o = new O();
						this.offset = o.unmarshal(this.buf, this.offset, this.i);
						return o;
					} catch (BufferUnderflowException e) {
					}
				}
				// not enough data

				if (this.i <= this.offset) {
					this.offset = 0;
					this.i = 0;
				} else if (i == buf.length) {
					byte[] src = this.buf;
					// TODO: better size estimation
					if (offset == 0) this.buf = new byte[Math.min(O.colferSizeMax, this.buf.length * 4)];
					System.arraycopy(src, this.offset, this.buf, 0, this.i - this.offset);
					this.i -= this.offset;
					this.offset = 0;
				}
				assert this.i < this.buf.length;

				int n = in.read(buf, i, buf.length - i);
				if (n < 0) {
					if (this.i > this.offset)
						throw new InputMismatchException("colfer: pending data with EOF");
					return null;
				}
				assert n > 0;
				i += n;
			}
		}

	}


	/**
	 * Serializes the object.
	 * All {@code null} elements in {@link #os} will be replaced with a {@code new} value.
	 * All {@code null} elements in {@link #ss} will be replaced with {@code ""}.
	 * All {@code null} elements in {@link #as} will be replaced with an empty byte array.
	 * @param out the data destination.
	 * @param buf the initial buffer or {@code null}.
	 * @return the final buffer. When the serial fits into {@code buf} then the return is {@code buf}.
	 *  Otherwise the return is a new buffer, large enough to hold the whole serial.
	 * @throws IOException from {@code out}.
	 * @throws IllegalStateException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 */
	public byte[] marshal(OutputStream out, byte[] buf) throws IOException {
		// TODO: better size estimation
		if (buf == null || buf.length == 0)
			buf = new byte[Math.min(O.colferSizeMax, 2048)];

		while (true) {
			int i;
			try {
				i = marshal(buf, 0);
			} catch (BufferOverflowException e) {
				buf = new byte[Math.min(O.colferSizeMax, buf.length * 4)];
				continue;
			}

			out.write(buf, 0, i);
			return buf;
		}
	}

	/**
	 * Serializes the object.
	 * All {@code null} elements in {@link #os} will be replaced with a {@code new} value.
	 * All {@code null} elements in {@link #ss} will be replaced with {@code ""}.
	 * All {@code null} elements in {@link #as} will be replaced with an empty byte array.
	 * @param buf the data destination.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferOverflowException when {@code buf} is too small.
	 * @throws IllegalStateException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 */
	public int marshal(byte[] buf, int offset) {
		int i = offset;

		try {
			if (this.b) {
				buf[i++] = (byte) 0;
			}

			if (this.u32 != 0) {
				int x = this.u32;
				if ((x & ~((1 << 21) - 1)) != 0) {
					buf[i++] = (byte) (1 | 0x80);
					buf[i++] = (byte) (x >>> 24);
					buf[i++] = (byte) (x >>> 16);
					buf[i++] = (byte) (x >>> 8);
				} else {
					buf[i++] = (byte) 1;
					while (x > 0x7f) {
						buf[i++] = (byte) (x | 0x80);
						x >>>= 7;
					}
				}
				buf[i++] = (byte) x;
			}

			if (this.u64 != 0) {
				long x = this.u64;
				if ((x & ~((1L << 49) - 1)) != 0) {
					buf[i++] = (byte) (2 | 0x80);
					buf[i++] = (byte) (x >>> 56);
					buf[i++] = (byte) (x >>> 48);
					buf[i++] = (byte) (x >>> 40);
					buf[i++] = (byte) (x >>> 32);
					buf[i++] = (byte) (x >>> 24);
					buf[i++] = (byte) (x >>> 16);
					buf[i++] = (byte) (x >>> 8);
					buf[i++] = (byte) (x);
				} else {
					buf[i++] = (byte) 2;
					while (x > 0x7fL) {
						buf[i++] = (byte) (x | 0x80);
						x >>>= 7;
					}
					buf[i++] = (byte) x;
				}
			}

			if (this.i32 != 0) {
				int x = this.i32;
				if (x < 0) {
					x = -x;
					buf[i++] = (byte) (3 | 0x80);
				} else
					buf[i++] = (byte) 3;
				while ((x & ~0x7f) != 0) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;
			}

			if (this.i64 != 0) {
				long x = this.i64;
				if (x < 0) {
					x = -x;
					buf[i++] = (byte) (4 | 0x80);
				} else
					buf[i++] = (byte) 4;
				for (int n = 0; n < 8 && (x & ~0x7fL) != 0; n++) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;
			}

			if (this.f32 != 0.0f) {
				buf[i++] = (byte) 5;
				int x = Float.floatToRawIntBits(this.f32);
				buf[i++] = (byte) (x >>> 24);
				buf[i++] = (byte) (x >>> 16);
				buf[i++] = (byte) (x >>> 8);
				buf[i++] = (byte) (x);
			}

			if (this.f64 != 0.0) {
				buf[i++] = (byte) 6;
				long x = Double.doubleToRawLongBits(this.f64);
				buf[i++] = (byte) (x >>> 56);
				buf[i++] = (byte) (x >>> 48);
				buf[i++] = (byte) (x >>> 40);
				buf[i++] = (byte) (x >>> 32);
				buf[i++] = (byte) (x >>> 24);
				buf[i++] = (byte) (x >>> 16);
				buf[i++] = (byte) (x >>> 8);
				buf[i++] = (byte) (x);
			}

			if (this.t != null) {
				long s = this.t.getEpochSecond();
				int ns = this.t.getNano();
				if (s != 0 || ns != 0) {
					if (s >= 0 && s < (1L << 32)) {
						buf[i++] = (byte) 7;
						buf[i++] = (byte) (s >>> 24);
						buf[i++] = (byte) (s >>> 16);
						buf[i++] = (byte) (s >>> 8);
						buf[i++] = (byte) (s);
						buf[i++] = (byte) (ns >>> 24);
						buf[i++] = (byte) (ns >>> 16);
						buf[i++] = (byte) (ns >>> 8);
						buf[i++] = (byte) (ns);
					} else {
						buf[i++] = (byte) (7 | 0x80);
						buf[i++] = (byte) (s >>> 56);
						buf[i++] = (byte) (s >>> 48);
						buf[i++] = (byte) (s >>> 40);
						buf[i++] = (byte) (s >>> 32);
						buf[i++] = (byte) (s >>> 24);
						buf[i++] = (byte) (s >>> 16);
						buf[i++] = (byte) (s >>> 8);
						buf[i++] = (byte) (s);
						buf[i++] = (byte) (ns >>> 24);
						buf[i++] = (byte) (ns >>> 16);
						buf[i++] = (byte) (ns >>> 8);
						buf[i++] = (byte) (ns);
					}
				}
			}

			if (! this.s.isEmpty()) {
				buf[i++] = (byte) 8;
				int start = ++i;

				String s = this.s;
				for (int sIndex = 0, sLength = s.length(); sIndex < sLength; sIndex++) {
					char c = s.charAt(sIndex);
					if (c < '\u0080') {
						buf[i++] = (byte) c;
					} else if (c < '\u0800') {
						buf[i++] = (byte) (192 | c >>> 6);
						buf[i++] = (byte) (128 | c & 63);
					} else if (c < '\ud800' || c > '\udfff') {
						buf[i++] = (byte) (224 | c >>> 12);
						buf[i++] = (byte) (128 | c >>> 6 & 63);
						buf[i++] = (byte) (128 | c & 63);
					} else {
						int cp = 0;
						if (++sIndex < sLength) cp = Character.toCodePoint(c, s.charAt(sIndex));
						if ((cp >= 1 << 16) && (cp < 1 << 21)) {
							buf[i++] = (byte) (240 | cp >>> 18);
							buf[i++] = (byte) (128 | cp >>> 12 & 63);
							buf[i++] = (byte) (128 | cp >>> 6 & 63);
							buf[i++] = (byte) (128 | cp & 63);
						} else
							buf[i++] = (byte) '?';
					}
				}
				int size = i - start;
				if (size > O.colferSizeMax)
					throw new IllegalStateException(format("colfer: gen.o.s size %d exceeds %d UTF-8 bytes", size, O.colferSizeMax));

				int ii = start - 1;
				if (size > 0x7f) {
					i++;
					for (int x = size; x >= 1 << 14; x >>>= 7) i++;
					System.arraycopy(buf, start, buf, i - size, size);

					do {
						buf[ii++] = (byte) (size | 0x80);
						size >>>= 7;
					} while (size > 0x7f);
				}
				buf[ii] = (byte) size;
			}

			if (this.a.length != 0) {
				buf[i++] = (byte) 9;

				int size = this.a.length;
				if (size > O.colferSizeMax)
					throw new IllegalStateException(format("colfer: gen.o.a size %d exceeds %d bytes", size, O.colferSizeMax));

				int x = size;
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				int start = i;
				i += size;
				System.arraycopy(this.a, 0, buf, start, size);
			}

			if (this.o != null) {
				buf[i++] = (byte) 10;
				i = this.o.marshal(buf, i);
			}

			if (this.os.length != 0) {
				buf[i++] = (byte) 11;
				O[] a = this.os;

				int x = a.length;
				if (x > O.colferListMax)
					throw new IllegalStateException(format("colfer: gen.o.os length %d exceeds %d elements", x, O.colferListMax));
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				for (int ai = 0; ai < a.length; ai++) {
					O o = a[ai];
					if (o == null) {
						o = new O();
						a[ai] = o;
					}
					i = o.marshal(buf, i);
				}
			}

			if (this.ss.length != 0) {
				buf[i++] = (byte) 12;
				String[] a = this.ss;

				int x = a.length;
				if (x > O.colferListMax)
					throw new IllegalStateException(format("colfer: gen.o.ss length %d exceeds %d elements", x, O.colferListMax));
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				for (int ai = 0; ai < a.length; ai++) {
					String s = a[ai];
					if (s == null) {
						s = "";
						a[ai] = s;
					}

					int start = ++i;

					for (int sIndex = 0, sLength = s.length(); sIndex < sLength; sIndex++) {
						char c = s.charAt(sIndex);
						if (c < '\u0080') {
							buf[i++] = (byte) c;
						} else if (c < '\u0800') {
							buf[i++] = (byte) (192 | c >>> 6);
							buf[i++] = (byte) (128 | c & 63);
						} else if (c < '\ud800' || c > '\udfff') {
							buf[i++] = (byte) (224 | c >>> 12);
							buf[i++] = (byte) (128 | c >>> 6 & 63);
							buf[i++] = (byte) (128 | c & 63);
						} else {
							int cp = 0;
							if (++sIndex < sLength) cp = Character.toCodePoint(c, s.charAt(sIndex));
							if ((cp >= 1 << 16) && (cp < 1 << 21)) {
								buf[i++] = (byte) (240 | cp >>> 18);
								buf[i++] = (byte) (128 | cp >>> 12 & 63);
								buf[i++] = (byte) (128 | cp >>> 6 & 63);
								buf[i++] = (byte) (128 | cp & 63);
							} else
								buf[i++] = (byte) '?';
						}
					}
					int size = i - start;
					if (size > O.colferSizeMax)
						throw new IllegalStateException(format("colfer: gen.o.ss[%d] size %d exceeds %d UTF-8 bytes", ai, size, O.colferSizeMax));

					int ii = start - 1;
					if (size > 0x7f) {
						i++;
						for (int y = size; y >= 1 << 14; y >>>= 7) i++;
						System.arraycopy(buf, start, buf, i - size, size);

						do {
							buf[ii++] = (byte) (size | 0x80);
							size >>>= 7;
						} while (size > 0x7f);
					}
					buf[ii] = (byte) size;
				}
			}

			if (this.as.length != 0) {
				buf[i++] = (byte) 13;
				byte[][] a = this.as;

				int x = a.length;
				if (x > O.colferListMax)
					throw new IllegalStateException(format("colfer: gen.o.as length %d exceeds %d elements", x, O.colferListMax));
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				for (int ai = 0; ai < a.length; ai++) {
					byte[] b = a[ai];
					if (b == null) {
						b = _zeroBytes;
						a[ai] = b;
					}
					if (b.length > O.colferSizeMax)
						throw new IllegalStateException(format("colfer: gen.o.as[%d] size %d exceeds %d bytes", ai, b.length, O.colferSizeMax));

					x = b.length;
					while (x > 0x7f) {
						buf[i++] = (byte) (x | 0x80);
						x >>>= 7;
					}
					buf[i++] = (byte) x;

					int start = i;
					i += b.length;
					System.arraycopy(b, 0, buf, start, b.length);
				}
			}

			if (this.u8 != 0) {
				buf[i++] = (byte) 14;
				buf[i++] = this.u8;
			}

			if (this.u16 != 0) {
				short x = this.u16;
				if ((x & (short)0xff00) != 0) {
					buf[i++] = (byte) 15;
					buf[i++] = (byte) (x >>> 8);
				} else {
					buf[i++] = (byte) (15 | 0x80);
				}
				buf[i++] = (byte) x;
			}

			if (this.f32s.length != 0) {
				buf[i++] = (byte) 16;
				float[] a = this.f32s;

				int l = a.length;
				if (l > O.colferListMax)
					throw new IllegalStateException(format("colfer: gen.o.f32s length %d exceeds %d elements", l, O.colferListMax));
				while (l > 0x7f) {
					buf[i++] = (byte) (l | 0x80);
					l >>>= 7;
				}
				buf[i++] = (byte) l;

				for (float f : a) {
					int x = Float.floatToRawIntBits(f);
					buf[i++] = (byte) (x >>> 24);
					buf[i++] = (byte) (x >>> 16);
					buf[i++] = (byte) (x >>> 8);
					buf[i++] = (byte) (x);
				}
			}

			if (this.f64s.length != 0) {
				buf[i++] = (byte) 17;
				double[] a = this.f64s;

				int l = a.length;
				if (l > O.colferListMax)
					throw new IllegalStateException(format("colfer: gen.o.f64s length %d exceeds %d elements", l, O.colferListMax));
				while (l > 0x7f) {
					buf[i++] = (byte) (l | 0x80);
					l >>>= 7;
				}
				buf[i++] = (byte) l;

				for (double f : a) {
					long x = Double.doubleToRawLongBits(f);
					buf[i++] = (byte) (x >>> 56);
					buf[i++] = (byte) (x >>> 48);
					buf[i++] = (byte) (x >>> 40);
					buf[i++] = (byte) (x >>> 32);
					buf[i++] = (byte) (x >>> 24);
					buf[i++] = (byte) (x >>> 16);
					buf[i++] = (byte) (x >>> 8);
					buf[i++] = (byte) (x);
				}
			}

			buf[i++] = (byte) 0x7f;
			return i;
		} catch (ArrayIndexOutOfBoundsException e) {
			if (i - offset > O.colferSizeMax)
				throw new IllegalStateException(format("colfer: gen.o exceeds %d bytes", O.colferSizeMax));
			if (i > buf.length) throw new BufferOverflowException();
			throw e;
		}
	}

	/**
	 * Deserializes the object.
	 * @param buf the data source.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferUnderflowException when {@code buf} is incomplete. (EOF)
	 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 * @throws InputMismatchException when the data does not match this object's schema.
	 */
	public int unmarshal(byte[] buf, int offset) {
		return unmarshal(buf, offset, buf.length);
	}

	/**
	 * Deserializes the object.
	 * @param buf the data source.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @param end the index limit for {@code buf}, exclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferUnderflowException when {@code buf} is incomplete. (EOF)
	 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 * @throws InputMismatchException when the data does not match this object's schema.
	 */
	public int unmarshal(byte[] buf, int offset, int end) {
		if (end > buf.length) end = buf.length;
		int i = offset;

		try {
			byte header = buf[i++];

			if (header == (byte) 0) {
				this.b = true;
				header = buf[i++];
			}

			if (header == (byte) 1) {
				int x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					x |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				this.u32 = x;
				header = buf[i++];
			} else if (header == (byte) (1 | 0x80)) {
				this.u32 = (buf[i++] & 0xff) << 24 | (buf[i++] & 0xff) << 16 | (buf[i++] & 0xff) << 8 | (buf[i++] & 0xff);
				header = buf[i++];
			}

			if (header == (byte) 2) {
				long x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					if (shift == 56 || b >= 0) {
						x |= (b & 0xffL) << shift;
						break;
					}
					x |= (b & 0x7fL) << shift;
				}
				this.u64 = x;
				header = buf[i++];
			} else if (header == (byte) (2 | 0x80)) {
				this.u64 = (buf[i++] & 0xffL) << 56 | (buf[i++] & 0xffL) << 48 | (buf[i++] & 0xffL) << 40 | (buf[i++] & 0xffL) << 32
					| (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				header = buf[i++];
			}

			if (header == (byte) 3) {
				int x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					x |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				this.i32 = x;
				header = buf[i++];
			} else if (header == (byte) (3 | 0x80)) {
				int x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					x |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				this.i32 = -x;
				header = buf[i++];
			}

			if (header == (byte) 4) {
				long x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					if (shift == 56 || b >= 0) {
						x |= (b & 0xffL) << shift;
						break;
					}
					x |= (b & 0x7fL) << shift;
				}
				this.i64 = x;
				header = buf[i++];
			} else if (header == (byte) (4 | 0x80)) {
				long x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					if (shift == 56 || b >= 0) {
						x |= (b & 0xffL) << shift;
						break;
					}
					x |= (b & 0x7fL) << shift;
				}
				this.i64 = -x;
				header = buf[i++];
			}

			if (header == (byte) 5) {
				int x = (buf[i++] & 0xff) << 24 | (buf[i++] & 0xff) << 16 | (buf[i++] & 0xff) << 8 | (buf[i++] & 0xff);
				this.f32 = Float.intBitsToFloat(x);
				header = buf[i++];
			}

			if (header == (byte) 6) {
				long x = (buf[i++] & 0xffL) << 56 | (buf[i++] & 0xffL) << 48 | (buf[i++] & 0xffL) << 40 | (buf[i++] & 0xffL) << 32
					| (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				this.f64 = Double.longBitsToDouble(x);
				header = buf[i++];
			}

			if (header == (byte) 7) {
				long s = (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				long ns = (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				this.t = java.time.Instant.ofEpochSecond(s, ns);
				header = buf[i++];
			} else if (header == (byte) (7 | 0x80)) {
				long s = (buf[i++] & 0xffL) << 56 | (buf[i++] & 0xffL) << 48 | (buf[i++] & 0xffL) << 40 | (buf[i++] & 0xffL) << 32
					| (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				long ns = (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
				this.t = java.time.Instant.ofEpochSecond(s, ns);
				header = buf[i++];
			}

			if (header == (byte) 8) {
				int size = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					size |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (size < 0 || size > O.colferSizeMax)
					throw new SecurityException(format("colfer: gen.o.s size %d exceeds %d UTF-8 bytes", size, O.colferSizeMax));

				int start = i;
				i += size;
				this.s = new String(buf, start, size, StandardCharsets.UTF_8);
				header = buf[i++];
			}

			if (header == (byte) 9) {
				int size = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					size |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (size < 0 || size > O.colferSizeMax)
					throw new SecurityException(format("colfer: gen.o.a size %d exceeds %d bytes", size, O.colferSizeMax));

				this.a = new byte[size];
				int start = i;
				i += size;
				System.arraycopy(buf, start, this.a, 0, size);

				header = buf[i++];
			}

			if (header == (byte) 10) {
				this.o = new O();
				i = this.o.unmarshal(buf, i, end);
				header = buf[i++];
			}

			if (header == (byte) 11) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > O.colferListMax)
					throw new SecurityException(format("colfer: gen.o.os length %d exceeds %d elements", length, O.colferListMax));

				O[] a = new O[length];
				for (int ai = 0; ai < length; ai++) {
					O o = new O();
					i = o.unmarshal(buf, i, end);
					a[ai] = o;
				}
				this.os = a;
				header = buf[i++];
			}

			if (header == (byte) 12) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > O.colferListMax)
					throw new SecurityException(format("colfer: gen.o.ss length %d exceeds %d elements", length, O.colferListMax));

				String[] a = new String[length];
				for (int ai = 0; ai < length; ai++) {
					int size = 0;
					for (int shift = 0; true; shift += 7) {
						byte b = buf[i++];
						size |= (b & 0x7f) << shift;
						if (shift == 28 || b >= 0) break;
					}
					if (size < 0 || size > O.colferSizeMax)
						throw new SecurityException(format("colfer: gen.o.ss[%d] size %d exceeds %d UTF-8 bytes", ai, size, O.colferSizeMax));

					int start = i;
					i += size;
					a[ai] = new String(buf, start, size, StandardCharsets.UTF_8);
				}
				this.ss = a;
				header = buf[i++];
			}

			if (header == (byte) 13) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > O.colferListMax)
					throw new SecurityException(format("colfer: gen.o.as length %d exceeds %d elements", length, O.colferListMax));

				byte[][] a = new byte[length][];
				for (int ai = 0; ai < length; ai++) {
					int size = 0;
					for (int shift = 0; true; shift += 7) {
						byte b = buf[i++];
						size |= (b & 0x7f) << shift;
						if (shift == 28 || b >= 0) break;
					}
					if (size < 0 || size > O.colferSizeMax)
						throw new SecurityException(format("colfer: gen.o.as[%d] size %d exceeds %d bytes", ai, size, O.colferSizeMax));

					byte[] e = new byte[size];
					int start = i;
					i += size;
					System.arraycopy(buf, start, e, 0, size);
					a[ai] = e;
				}
				this.as = a;

				header = buf[i++];
			}

			if (header == (byte) 14) {
				this.u8 = buf[i++];
				header = buf[i++];
			}

			if (header == (byte) 15) {
				this.u16 = (short) ((buf[i++] & 0xff) << 8 | (buf[i++] & 0xff));
				header = buf[i++];
			} else if (header == (byte) (15 | 0x80)) {
				this.u16 = (short) (buf[i++] & 0xff);
				header = buf[i++];
			}

			if (header == (byte) 16) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > O.colferListMax)
					throw new SecurityException(format("colfer: gen.o.f32s length %d exceeds %d elements", length, O.colferListMax));

				float[] a = new float[length];
				for (int ai = 0; ai < length; ai++) {
					int x = (buf[i++] & 0xff) << 24 | (buf[i++] & 0xff) << 16 | (buf[i++] & 0xff) << 8 | (buf[i++] & 0xff);
					a[ai] = Float.intBitsToFloat(x);
				}
				this.f32s = a;
				header = buf[i++];
			}

			if (header == (byte) 17) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > O.colferListMax)
					throw new SecurityException(format("colfer: gen.o.f64s length %d exceeds %d elements", length, O.colferListMax));

				double[] a = new double[length];
				for (int ai = 0; ai < length; ai++) {
					long x = (buf[i++] & 0xffL) << 56 | (buf[i++] & 0xffL) << 48 | (buf[i++] & 0xffL) << 40 | (buf[i++] & 0xffL) << 32
						| (buf[i++] & 0xffL) << 24 | (buf[i++] & 0xffL) << 16 | (buf[i++] & 0xffL) << 8 | (buf[i++] & 0xffL);
					a[ai] = Double.longBitsToDouble(x);
				}
				this.f64s = a;
				header = buf[i++];
			}

			if (header != (byte) 0x7f)
				throw new InputMismatchException(format("colfer: unknown header at byte %d", i - 1));
		} finally {
			if (i > end && end - offset < O.colferSizeMax) throw new BufferUnderflowException();
			if (i < 0 || i - offset > O.colferSizeMax)
				throw new SecurityException(format("colfer: gen.o exceeds %d bytes", O.colferSizeMax));
			if (i > end) throw new BufferUnderflowException();
		}

		return i;
	}

	// {@link Serializable} version number.
	private static final long serialVersionUID = 18L;

	// {@link Serializable} Colfer extension.
	private void writeObject(ObjectOutputStream out) throws IOException {
		// TODO: better size estimation
		byte[] buf = new byte[1024];
		int n;
		while (true) try {
			n = marshal(buf, 0);
			break;
		} catch (BufferUnderflowException e) {
			buf = new byte[4 * buf.length];
		}

		out.writeInt(n);
		out.write(buf, 0, n);
	}

	// {@link Serializable} Colfer extension.
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		init();

		int n = in.readInt();
		byte[] buf = new byte[n];
		in.readFully(buf);
		unmarshal(buf, 0);
	}

	// {@link Serializable} Colfer extension.
	private void readObjectNoData() throws ObjectStreamException {
		init();
	}

	/**
	 * Gets gen.o.b.
	 * @return the value.
	 */
	public boolean getB() {
		return this.b;
	}

	/**
	 * Sets gen.o.b.
	 * @param value the replacement.
	 */
	public void setB(boolean value) {
		this.b = value;
	}

	/**
	 * Sets gen.o.b.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withB(boolean value) {
		this.b = value;
		return this;
	}

	/**
	 * Gets gen.o.u32.
	 * @return the value.
	 */
	public int getU32() {
		return this.u32;
	}

	/**
	 * Sets gen.o.u32.
	 * @param value the replacement.
	 */
	public void setU32(int value) {
		this.u32 = value;
	}

	/**
	 * Sets gen.o.u32.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withU32(int value) {
		this.u32 = value;
		return this;
	}

	/**
	 * Gets gen.o.u64.
	 * @return the value.
	 */
	public long getU64() {
		return this.u64;
	}

	/**
	 * Sets gen.o.u64.
	 * @param value the replacement.
	 */
	public void setU64(long value) {
		this.u64 = value;
	}

	/**
	 * Sets gen.o.u64.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withU64(long value) {
		this.u64 = value;
		return this;
	}

	/**
	 * Gets gen.o.i32.
	 * @return the value.
	 */
	public int getI32() {
		return this.i32;
	}

	/**
	 * Sets gen.o.i32.
	 * @param value the replacement.
	 */
	public void setI32(int value) {
		this.i32 = value;
	}

	/**
	 * Sets gen.o.i32.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withI32(int value) {
		this.i32 = value;
		return this;
	}

	/**
	 * Gets gen.o.i64.
	 * @return the value.
	 */
	public long getI64() {
		return this.i64;
	}

	/**
	 * Sets gen.o.i64.
	 * @param value the replacement.
	 */
	public void setI64(long value) {
		this.i64 = value;
	}

	/**
	 * Sets gen.o.i64.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withI64(long value) {
		this.i64 = value;
		return this;
	}

	/**
	 * Gets gen.o.f32.
	 * @return the value.
	 */
	public float getF32() {
		return this.f32;
	}

	/**
	 * Sets gen.o.f32.
	 * @param value the replacement.
	 */
	public void setF32(float value) {
		this.f32 = value;
	}

	/**
	 * Sets gen.o.f32.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withF32(float value) {
		this.f32 = value;
		return this;
	}

	/**
	 * Gets gen.o.f64.
	 * @return the value.
	 */
	public double getF64() {
		return this.f64;
	}

	/**
	 * Sets gen.o.f64.
	 * @param value the replacement.
	 */
	public void setF64(double value) {
		this.f64 = value;
	}

	/**
	 * Sets gen.o.f64.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withF64(double value) {
		this.f64 = value;
		return this;
	}

	/**
	 * Gets gen.o.t.
	 * @return the value.
	 */
	public java.time.Instant getT() {
		return this.t;
	}

	/**
	 * Sets gen.o.t.
	 * @param value the replacement.
	 */
	public void setT(java.time.Instant value) {
		this.t = value;
	}

	/**
	 * Sets gen.o.t.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withT(java.time.Instant value) {
		this.t = value;
		return this;
	}

	/**
	 * Gets gen.o.s.
	 * @return the value.
	 */
	public String getS() {
		return this.s;
	}

	/**
	 * Sets gen.o.s.
	 * @param value the replacement.
	 */
	public void setS(String value) {
		this.s = value;
	}

	/**
	 * Sets gen.o.s.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withS(String value) {
		this.s = value;
		return this;
	}

	/**
	 * Gets gen.o.a.
	 * @return the value.
	 */
	public byte[] getA() {
		return this.a;
	}

	/**
	 * Sets gen.o.a.
	 * @param value the replacement.
	 */
	public void setA(byte[] value) {
		this.a = value;
	}

	/**
	 * Sets gen.o.a.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withA(byte[] value) {
		this.a = value;
		return this;
	}

	/**
	 * Gets gen.o.o.
	 * @return the value.
	 */
	public O getO() {
		return this.o;
	}

	/**
	 * Sets gen.o.o.
	 * @param value the replacement.
	 */
	public void setO(O value) {
		this.o = value;
	}

	/**
	 * Sets gen.o.o.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withO(O value) {
		this.o = value;
		return this;
	}

	/**
	 * Gets gen.o.os.
	 * @return the value.
	 */
	public O[] getOs() {
		return this.os;
	}

	/**
	 * Sets gen.o.os.
	 * @param value the replacement.
	 */
	public void setOs(O[] value) {
		this.os = value;
	}

	/**
	 * Sets gen.o.os.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withOs(O[] value) {
		this.os = value;
		return this;
	}

	/**
	 * Gets gen.o.ss.
	 * @return the value.
	 */
	public String[] getSs() {
		return this.ss;
	}

	/**
	 * Sets gen.o.ss.
	 * @param value the replacement.
	 */
	public void setSs(String[] value) {
		this.ss = value;
	}

	/**
	 * Sets gen.o.ss.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withSs(String[] value) {
		this.ss = value;
		return this;
	}

	/**
	 * Gets gen.o.as.
	 * @return the value.
	 */
	public byte[][] getAs() {
		return this.as;
	}

	/**
	 * Sets gen.o.as.
	 * @param value the replacement.
	 */
	public void setAs(byte[][] value) {
		this.as = value;
	}

	/**
	 * Sets gen.o.as.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withAs(byte[][] value) {
		this.as = value;
		return this;
	}

	/**
	 * Gets gen.o.u8.
	 * @return the value.
	 */
	public byte getU8() {
		return this.u8;
	}

	/**
	 * Sets gen.o.u8.
	 * @param value the replacement.
	 */
	public void setU8(byte value) {
		this.u8 = value;
	}

	/**
	 * Sets gen.o.u8.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withU8(byte value) {
		this.u8 = value;
		return this;
	}

	/**
	 * Gets gen.o.u16.
	 * @return the value.
	 */
	public short getU16() {
		return this.u16;
	}

	/**
	 * Sets gen.o.u16.
	 * @param value the replacement.
	 */
	public void setU16(short value) {
		this.u16 = value;
	}

	/**
	 * Sets gen.o.u16.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withU16(short value) {
		this.u16 = value;
		return this;
	}

	/**
	 * Gets gen.o.f32s.
	 * @return the value.
	 */
	public float[] getF32s() {
		return this.f32s;
	}

	/**
	 * Sets gen.o.f32s.
	 * @param value the replacement.
	 */
	public void setF32s(float[] value) {
		this.f32s = value;
	}

	/**
	 * Sets gen.o.f32s.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withF32s(float[] value) {
		this.f32s = value;
		return this;
	}

	/**
	 * Gets gen.o.f64s.
	 * @return the value.
	 */
	public double[] getF64s() {
		return this.f64s;
	}

	/**
	 * Sets gen.o.f64s.
	 * @param value the replacement.
	 */
	public void setF64s(double[] value) {
		this.f64s = value;
	}

	/**
	 * Sets gen.o.f64s.
	 * @param value the replacement.
	 * @return {@code this}.
	 */
	public O withF64s(double[] value) {
		this.f64s = value;
		return this;
	}

	@Override
	public final int hashCode() {
		int h = 1;
		h = 31 * h + (this.b ? 1231 : 1237);
		h = 31 * h + this.u32;
		h = 31 * h + (int)(this.u64 ^ this.u64 >>> 32);
		h = 31 * h + this.i32;
		h = 31 * h + (int)(this.i64 ^ this.i64 >>> 32);
		h = 31 * h + Float.floatToIntBits(this.f32);
		long _f64Bits = Double.doubleToLongBits(this.f64);
		h = 31 * h + (int) (_f64Bits ^ _f64Bits >>> 32);
		if (this.t != null) h = 31 * h + this.t.hashCode();
		if (this.s != null) h = 31 * h + this.s.hashCode();
		for (byte b : this.a) h = 31 * h + b;
		if (this.o != null) h = 31 * h + this.o.hashCode();
		for (O o : this.os) h = 31 * h + (o == null ? 0 : o.hashCode());
		for (String o : this.ss) h = 31 * h + (o == null ? 0 : o.hashCode());
		for (byte[] b : this.as) h = 31 * h + java.util.Arrays.hashCode(b);
		h = 31 * h + (this.u8 & 0xff);
		h = 31 * h + (this.u16 & 0xffff);
		h = 31 * h + java.util.Arrays.hashCode(this.f32s);
		h = 31 * h + java.util.Arrays.hashCode(this.f64s);
		return h;
	}

	@Override
	public final boolean equals(Object o) {
		return o instanceof O && equals((O) o);
	}

	public final boolean equals(O o) {
		if (o == null) return false;
		if (o == this) return true;

		return this.b == o.b
			&& this.u32 == o.u32
			&& this.u64 == o.u64
			&& this.i32 == o.i32
			&& this.i64 == o.i64
			&& (this.f32 == o.f32 || (this.f32 != this.f32 && o.f32 != o.f32))
			&& (this.f64 == o.f64 || (this.f64 != this.f64 && o.f64 != o.f64))
			&& (this.t == null ? o.t == null : this.t.equals(o.t))
			&& (this.s == null ? o.s == null : this.s.equals(o.s))
			&& java.util.Arrays.equals(this.a, o.a)
			&& (this.o == null ? o.o == null : this.o.equals(o.o))
			&& java.util.Arrays.equals(this.os, o.os)
			&& java.util.Arrays.equals(this.ss, o.ss)
			&& _equals(this.as, o.as)
			&& this.u8 == o.u8
			&& this.u16 == o.u16
			&& java.util.Arrays.equals(this.f32s, o.f32s)
			&& java.util.Arrays.equals(this.f64s, o.f64s);
	}

	private static boolean _equals(byte[][] a, byte[][] b) {
		if (a == b) return true;
		if (a == null || b == null) return false;

		int i = a.length;
		if (i != b.length) return false;

		while (--i >= 0) if (! java.util.Arrays.equals(a[i], b[i])) return false;
		return true;
	}

}
